package ptit.example.btlwebbook.dto.request;

import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateToCartDTO {
    private long bookId;
    @Min(value = 0, message = "quantity min equal 0")
    private int quantity;
}
