package ptit.example.btlwebbook.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import ptit.example.btlwebbook.model.CostInfo;
import ptit.example.btlwebbook.model.DeliveryInfo;
import ptit.example.btlwebbook.model.Order;
import ptit.example.btlwebbook.model.OrderDetail;

import java.text.DecimalFormat;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailService {
    private final JavaMailSender mailSender;
    @Value("${spring.mail.username}")
    private String fromEmail;
    /**
     * Gửi email chứa liên kết đặt lại mật khẩu
     *
     * @param toEmail        Địa chỉ email người nhận
     * @param resetToken  Token đặt lại mật khẩu
     * @throws MessagingException Nếu có lỗi khi gửi email
     */
    public void sendResetPasswordEmail(String toEmail, String resetToken) throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");

        // URL cơ sở cho liên kết đặt lại mật khẩu (có thể cấu hình trong application.properties)
        String resetUrl = "http://localhost:3000/dat-lai-mat-khau/?token=" + resetToken;

        // Nội dung email (HTML)
        String htmlContent = "<h3>Đặt lại mật khẩu</h3>" +
                "<p>Vui lòng nhấp vào liên kết dưới đây để đặt lại mật khẩu của bạn:</p>" +
                "<p><a href=\"" + resetUrl + "\">Đặt lại mật khẩu</a></p>" +
                "<p>Liên kết này sẽ hết hạn sau 1 giờ.</p>" +
                "<p>Nếu bạn không yêu cầu đặt lại mật khẩu, vui lòng bỏ qua email này.</p>";

        helper.setTo(toEmail);
        helper.setSubject("Yêu cầu đặt lại mật khẩu");
        helper.setText(htmlContent, true); // true: hỗ trợ HTML
        helper.setFrom(fromEmail); // Thay bằng email của bạn

        mailSender.send(mimeMessage);
        log.info("Đã gửi email đặt lại mật khẩu đến: {}", toEmail);
    }
    /**
     * Gửi email chứa liên kết xác nhận đăng ký
     *
     * @param to                Địa chỉ email người nhận
     * @param verificationToken Token xác nhận email
     * @throws MessagingException Nếu có lỗi khi gửi email
     */
    public void sendVerificationEmail(String to, String verificationToken) throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");

        // Đường dẫn đến trang xác nhận tài khoản
        String verificationUrl = "http://localhost:3000/auth/verify-email?token=" + verificationToken;

        // Nội dung email dạng HTML
        String htmlContent = "<h3>Xác nhận đăng ký tài khoản</h3>" +
                "<p>Cảm ơn bạn đã đăng ký tài khoản với chúng tôi! Vui lòng nhấp vào liên kết dưới đây để kích hoạt tài khoản của bạn:</p>" +
                "<p><a href=\"" + verificationUrl + "\">Kích hoạt tài khoản</a></p>" +
                "<p>Liên kết này sẽ hết hạn sau 24 giờ.</p>" +
                "<p>Nếu bạn không thực hiện đăng ký này, vui lòng bỏ qua email này.</p>";

        helper.setTo(to);
        helper.setSubject("Xác nhận đăng ký tài khoản");
        helper.setText(htmlContent, true);
        helper.setFrom(fromEmail);

        mailSender.send(mimeMessage);
        log.info("Đã gửi email xác nhận đăng ký đến: {}", to);
    }
    public void sendOrderSuccessEmail(Order order, String clientURL) throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
        clientURL = clientURL + "/don-hang";
        DeliveryInfo delivery = order.getDelivery();
        List<OrderDetail> products = order.getOrderDetails();
        CostInfo cost = order.getCost();
        String methodText = order.getMethod() != null ? order.getMethod().getText() : "Không xác định";

        // Thiết lập thông tin email
        helper.setTo(delivery.getEmail());
        helper.setSubject("Xác nhận đơn hàng từ BookStore");
        log.info("set html");
        helper.setText(buildOrderEmailHtml(clientURL, delivery, products, cost, methodText), true);
        log.info("chuan bi gui");
        // Gửi email
        mailSender.send(mimeMessage);
    }
    private String formatPrice(double value) {
        DecimalFormat formatter = new DecimalFormat("#,###.##");
        return formatter.format(value) + " VND";
    }
    private String buildOrderEmailHtml(String clientURL, DeliveryInfo delivery, List<OrderDetail> products,
                                       CostInfo cost, String methodText) {
        StringBuilder html = new StringBuilder();
        html.append("""
            <div style="margin:0px;padding:0px;color:rgb(32,32,32);font-size:14px;font-weight:normal;font-family:Helvetica,Arial,sans-serif!important;line-height:150%%!important">
                <div style="margin: auto; max-width: 730px">
                    <table style="width: 100%%">
                        <tbody>
                            <tr>
                                <td>
                                    <div style="padding: 30px; border-bottom: 10px solid #f0f0f0">
                                        <p style="margin:0; font-size: 23px; color: #0f146d; text-align: center">Cảm ơn bạn đã đặt hàng tại BookStore</p>
                                    </div>
                                    <div style="padding: 30px; border-bottom: 10px solid #f0f0f0">
                                        <h2>Xin chào %s,</h2>
                                        <p>BookStore đã nhận được yêu cầu đặt hàng của bạn và đang xử lý nhé. Bạn sẽ nhận được thông báo tiếp theo khi đơn hàng đã sẵn sàng được giao.</p>
                                        <div style="margin: auto; width: 200px">
                                            <a href="%s/don-hang" style="background-image: linear-gradient(to right, #fc8b00, #ff03f6); color: white; padding: 12px; font-weight: bold; width: 200px; display: block; text-align:center; text-decoration: none">Tình trạng đơn hàng</a>
                                        </div>
                                    </div>
                                    <div style="padding: 30px; border-bottom: 10px solid #f0f0f0">
                                        <div style="padding: 5px 0px 15px 0px; font-size: 16px">Đơn hàng được giao đến</div>
                                        <table style="width: 100%%">
                                            <tbody>
                                                <tr>
                                                    <td width="25%%" style="color:#0f146d;font-weight:bold">Tên:</td>
                                                    <td width="75%%">%s</td>
                                                </tr>
                                                <tr>
                                                    <td style="color:#0f146d;font-weight:bold">Địa chỉ nhà:</td>
                                                    <td>%s</td>
                                                </tr>
                                                <tr>
                                                    <td style="color:#0f146d;font-weight:bold">Điện thoại:</td>
                                                    <td>%s</td>
                                                </tr>
                                                <tr>
                                                    <td style="color:#0f146d;font-weight:bold">Email:</td>
                                                    <td><a style="color: #33a2b2!important" href="mailto:%s" target="_blank">%s</a></td>
                                                </tr>
                                            </tbody>
                                        </table>
                                    </div>
                                    <div style="padding: 30px; border-bottom: 10px solid #f0f0f0">
                                        <div style="padding: 5px 0px 15px 0px; font-size: 16px">Kiện hàng</div>
                                        <table style="width: 100%%">
                                            <tbody>
            """.formatted(
                delivery.getFullName(),
                clientURL,
                delivery.getFullName(),
                delivery.getAddress(),
                delivery.getPhoneNumber(),
                delivery.getEmail(),
                delivery.getEmail()
        ));

        for (OrderDetail product : products) {
            html.append("""
                <tr>
                    <td width="40%%" style="color:#0f146d;font-weight:bold">
                        <img style="width: 160px; margin-right: 10px; object-fit: cover;" src="%s" alt="" />
                    </td>
                    <td style="width:60%%">
                        <p style="margin: 5px; padding: 0">%s</p>
                        <p style="margin: 5px; padding: 0">Số lượng: %d</p>
                        <p style="margin: 5px; padding: 0">Giá: %s</p>
                        <p style="color: #f27c24; margin: 5px; padding: 0">Thành tiền: %s</p>
                    </td>
                </tr>
                """.formatted(
                    product.getBook().getImageUrl(),
                    product.getBook().getName(),
                    product.getQuantity(),
                    formatPrice(product.getPrice()),
                    formatPrice(product.getTotalItem())
            ));
        }

        html.append("""
                                            </tbody>
                                        </table>
                                    </div>
                                    <div style="padding: 30px; border-bottom: 10px solid #f0f0f0; line-height: 2">
                                        <table style="width: 100%%; border-bottom: 1px solid #d8d8d8">
                                            <tbody>
                                                <tr>
                                                    <td width="50%%" style="color:#0f146d;font-weight:bold">Thành tiền:</td>
                                                    <td align="right" width="50%%">%s</td>
                                                </tr>
                                                <tr>
                                                    <td width="50%%" style="color:#0f146d;font-weight:bold">Phí vận chuyển:</td>
                                                    <td align="right" width="50%%">%s</td>
                                                </tr>
                                                <tr>
                                                    <td width="50%%" style="color:#0f146d;font-weight:bold">Giảm giá:</td>
                                                    <td align="right" width="50%%">%s</td>
                                                </tr>
                                                <tr>
                                                    <td width="50%%" style="color:#0f146d;font-weight:bold">Tổng cộng:</td>
                                                    <td align="right" width="50%%" style="color: #f27c24; font-weight: bold">%s</td>
                                                </tr>
                                            </tbody>
                                        </table>
                                        <table style="width: 100%%">
                                            <tbody>
                                                <tr>
                                                    <td width="50%%" style="color:#0f146d;font-weight:bold">Hình thức thanh toán:</td>
                                                    <td align="right" width="50%%">%s</td>
                                                </tr>
                                            </tbody>
                                        </table>
                                    </div>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </div>
            </div>
            """.formatted(
                formatPrice(cost.getSubTotal()),
                formatPrice(cost.getShippingFee()),
                formatPrice(cost.getDiscount()),
                formatPrice(cost.getTotal()),
                methodText
        ));

        return html.toString();
    }
}

