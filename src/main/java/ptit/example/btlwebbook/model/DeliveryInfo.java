package ptit.example.btlwebbook.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

@Embeddable
@Data
public class DeliveryInfo {
    @Column(name = "delivery_full_name", nullable = false)
    private String fullName; // Họ tên

    @Column(name = "delivery_email", nullable = false)
    private String email; // Email

    @Column(name = "delivery_phone_number", nullable = false)
    private String phoneNumber; // Số điện thoại

    @Column(name = "delivery_address", nullable = false)
    private String address; // Địa chỉ
}
