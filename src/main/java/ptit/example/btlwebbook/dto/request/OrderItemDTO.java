package ptit.example.btlwebbook.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemDTO {
    private long bookId;
    private int quantity;
    private Double price;
    private Double totalItem;
}
