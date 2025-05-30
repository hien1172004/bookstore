package ptit.example.btlwebbook.dto.response;

import lombok.*;
import ptit.example.btlwebbook.model.*;
import ptit.example.btlwebbook.utils.ROLE;
import ptit.example.btlwebbook.utils.UserStatus;

import java.util.List;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponse {
    private Long id;
    private String email;
    private String fullName;
    private Integer gender;
    private String birthday;
    private String phoneNumber;
    private Avatar avatar;
    private String service;
    private String serviceId;
    private ROLE role;
    private UserStatus status;
    private List<ReviewResponse> reviews;
    private List<AddressResponse> addresses;
    private List<VoucherResponse> vouchers;
}
