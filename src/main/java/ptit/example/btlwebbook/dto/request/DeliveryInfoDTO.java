package ptit.example.btlwebbook.dto.request;

import jakarta.persistence.Column;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DeliveryInfoDTO {

    private String fullName; // Họ tên


    private String email; // Email


    private String phoneNumber; // Số điện thoại


    private String address; // Địa chỉ
}
