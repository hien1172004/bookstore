package ptit.example.btlwebbook.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ptit.example.btlwebbook.dto.request.VnpayRequest;
import ptit.example.btlwebbook.service.VnpayService;

@RestController
@RequestMapping("/api/vnpay")
@RequiredArgsConstructor
public class VnpayController {
    private final VnpayService vnpayService;

    @PostMapping
    public ResponseEntity<String> createPayment(@RequestBody @Valid VnpayRequest paymentRequest) {
        try {
            String paymentUrl = vnpayService.createPayment(paymentRequest);
            return ResponseEntity.ok(paymentUrl);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Đã xảy ra lỗi khi tạo thanh toán!");
        }
    }

    @GetMapping("/return")
    public ResponseEntity<String> returnPayment(@RequestParam("vnp_ResponseCode") String responseCode, @RequestParam(name = "vnp_TxnRef") String vnpTxnRef) {
        return vnpayService.handlePaymentReturn(responseCode, vnpTxnRef);
    }
    @GetMapping("/ipn")
    public ResponseEntity<String> handlePaymentNotification(@RequestParam("vnp_ResponseCode") String responseCode, @RequestParam(name = "vnp_TxnRef") String vnpTxnRef) {
        return vnpayService.handlePaymentReturn(responseCode, vnpTxnRef);
    }
}
