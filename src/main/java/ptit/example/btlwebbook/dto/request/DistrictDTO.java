package ptit.example.btlwebbook.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DistrictDTO {
    private int districtId;
    private String districtName;
}
