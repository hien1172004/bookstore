package ptit.example.btlwebbook.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import ptit.example.btlwebbook.utils.OrderStatus;
import ptit.example.btlwebbook.utils.TrackingStatus;

import java.time.LocalDateTime;
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
public class Tracking extends AbstractEntity {
    @Enumerated(EnumType.STRING)
    private OrderStatus status; // Trạng thái

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user; // Người dùng thực hiện thay đổi

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order; // Đơn hàng liên quan
}
