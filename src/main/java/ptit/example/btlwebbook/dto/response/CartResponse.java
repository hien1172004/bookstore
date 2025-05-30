package ptit.example.btlwebbook.dto.response;

import lombok.*;

import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartResponse {
    private long id;
    private List<CartItemResponse> items;
    private double totalPrice;
}
