package ptit.example.btlwebbook.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import ptit.example.btlwebbook.utils.OrderStatus;
import ptit.example.btlwebbook.utils.PaymentMethod;
import ptit.example.btlwebbook.utils.PaymentStatus;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "orders")
@SuperBuilder
public class Order extends AbstractEntity{
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    // Thông tin giao hàng
    @Embedded
    private DeliveryInfo delivery;

    // Mã giảm giá
    @ManyToOne
    @JoinColumn(name = "voucher_id")
    private Voucher voucher;

    // Chi phí
    @Embedded
    private CostInfo cost;

    // Phương thức thanh toán
    @Builder.Default
    @Enumerated(EnumType.STRING)
    private PaymentMethod method = PaymentMethod.CASH;

    // ID giao dịch từ bên thứ 3
    private String paymentId;

    // Trạng thái thanh
    @Builder.Default
    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus = PaymentStatus.UNPAID;

    // Trạng thái đơn
    @Builder.Default
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus = OrderStatus.AWAITING_CHECK_PAYMENT;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<Tracking> tracking;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderDetail> orderDetails;

}
