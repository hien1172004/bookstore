package ptit.example.btlwebbook.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ptit.example.btlwebbook.config.VnpayConfig;
import ptit.example.btlwebbook.dto.request.VnpayRequest;
import ptit.example.btlwebbook.model.Order;
import ptit.example.btlwebbook.utils.PaymentMethod;
import ptit.example.btlwebbook.utils.PaymentStatus;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.*;
@Service
public class VnpayService {
    @Autowired
    private OrderService orderService;
    public String createPayment(VnpayRequest paymentRequest) throws UnsupportedEncodingException {
        String vnp_Version = "2.1.0";
        String vnp_Command = "pay";
        String orderType = "other";

        long amount = 0;
        try {
            amount = Long.parseLong(paymentRequest.getAmount()) * 100;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Số tiền không hợp lệ");
        }

//        String bankCode = "NCB";
        String bankCode = "";
        String vnp_TxnRef = paymentRequest.getPaymentId();
        String vnp_IpAddr = "127.0.0.1";
        String vnp_TmnCode = VnpayConfig.vnp_TmnCode;

        Map<String, String> vnp_Params = new HashMap<>();
        vnp_Params.put("vnp_Version", vnp_Version);
        vnp_Params.put("vnp_Command", vnp_Command);
        vnp_Params.put("vnp_TmnCode", vnp_TmnCode);
        vnp_Params.put("vnp_Amount", String.valueOf(amount));
        vnp_Params.put("vnp_CurrCode", "VND");

        vnp_Params.put("vnp_BankCode", bankCode);
        vnp_Params.put("vnp_TxnRef", vnp_TxnRef);
        vnp_Params.put("vnp_OrderInfo", "Thanh toan don hang:" + vnp_TxnRef);
        vnp_Params.put("vnp_OrderType", orderType);
        vnp_Params.put("vnp_Locale", "vn");
//        vnp_Params.put("vnp_PayType", "QRCODE"); // Chỉ định thanh toán qua QR Code
        vnp_Params.put("vnp_ReturnUrl", VnpayConfig.vnp_ReturnUrl);
//         // URL nhận thông báo thanh toán (IPN)
        vnp_Params.put("vnp_IpAddr", vnp_IpAddr);

        Calendar cld = Calendar.getInstance(TimeZone.getTimeZone("Etc/GMT+7"));
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        String vnp_CreateDate = formatter.format(cld.getTime());
        vnp_Params.put("vnp_CreateDate", vnp_CreateDate);

        cld.add(Calendar.MINUTE, 15);
        String vnp_ExpireDate = formatter.format(cld.getTime());
        vnp_Params.put("vnp_ExpireDate", vnp_ExpireDate);

        List<String> fieldNames = new ArrayList<>(vnp_Params.keySet());
        Collections.sort(fieldNames);
        StringBuilder hashData = new StringBuilder();
        StringBuilder query = new StringBuilder();
        for (String fieldName : fieldNames) {
            String fieldValue = vnp_Params.get(fieldName);
            if ((fieldValue != null) && (!fieldValue.isEmpty())) {
                hashData.append(fieldName).append('=')
                        .append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII));
                query.append(URLEncoder.encode(fieldName, StandardCharsets.US_ASCII))
                        .append('=')
                        .append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII));
                query.append('&');
                hashData.append('&');
            }
        }

        if (!query.isEmpty())
            query.setLength(query.length() - 1);
        if (!hashData.isEmpty())
            hashData.setLength(hashData.length() - 1);

        String vnp_SecureHash = VnpayConfig.hmacSHA512(VnpayConfig.secretKey, hashData.toString());
        query.append("&vnp_SecureHash=").append(vnp_SecureHash);
        return VnpayConfig.vnp_PayUrl + "?" + query;
    }

    public ResponseEntity<String> handlePaymentReturn(String responseCode, String paymentId) {
        if ("00".equals(responseCode)) {
            orderService.updatePaymentStatusByPaymentId(paymentId, PaymentStatus.PAID, PaymentMethod.VNPAY);
            return ResponseEntity.status(HttpStatus.FOUND)
                    .location(URI.create("http://localhost:3000/payment/vnpay_return?responseCode=" + responseCode + "&paymentId=" + paymentId))
                    .body("Thanh toán thành công! Đang chuyển hướng...");
        } else {
            orderService.updatePaymentStatusByPaymentId(paymentId, PaymentStatus.FAILED, PaymentMethod.VNPAY);
            return ResponseEntity.status(HttpStatus.FOUND)
                    .location(URI.create("http://localhost:3000/payment/vnpay_return?responseCode=" + responseCode + "&paymentId=" + paymentId))
                    .body("Thanh toán thất bại! Mã lỗi: " + responseCode);
        }
    }
}
