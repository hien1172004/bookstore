package ptit.example.btlwebbook.dto.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddressDTO {
    private String address;
    private Integer provinceId;
    private Integer districtId;
    private String wardId;
}
