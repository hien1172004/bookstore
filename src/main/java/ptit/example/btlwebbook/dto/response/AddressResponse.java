package ptit.example.btlwebbook.dto.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddressResponse {
    private Long id;
    private String address;
    private Integer provinceId;
    private Integer districtId;
    private String wardId;
    private Long userId;
}
