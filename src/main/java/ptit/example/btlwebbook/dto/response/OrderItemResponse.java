package ptit.example.btlwebbook.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemResponse {
    private Long bookId;
    private int quantity;
    private Double price;
    private Double totalItem;
}
