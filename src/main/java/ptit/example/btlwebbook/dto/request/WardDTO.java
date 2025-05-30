package ptit.example.btlwebbook.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WardDTO {
    private int wardId;
    private String wardName;
}
