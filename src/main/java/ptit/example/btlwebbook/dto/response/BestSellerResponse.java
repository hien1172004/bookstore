package ptit.example.btlwebbook.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BestSellerResponse {
    private Long bookId;
    private String name;
    private Long quantity;
}
