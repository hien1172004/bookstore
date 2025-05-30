package ptit.example.btlwebbook.model;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Min;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
public class CartItem extends AbstractEntity{
    @ManyToOne
    @JoinColumn(name = "cart_id")
    private Cart cart;  // Mỗi CartItem thuộc về một Cart

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;  // Mỗi CartItem liên kết với một Book
    @Min(value = 1, message = "Số lượng phải lớn hơn 0")
    private int quantity;  // Số lượng của sản phẩm trong giỏ hàng
}
