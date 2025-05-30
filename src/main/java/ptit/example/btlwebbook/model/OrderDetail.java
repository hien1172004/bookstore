package ptit.example.btlwebbook.model;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@Entity
@Table(name = "order_detail")
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class OrderDetail extends AbstractEntity{
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;
    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;
    @Positive
    private int quantity;

    private Double price;

    private Double totalItem;
}