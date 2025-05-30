package ptit.example.btlwebbook.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ptit.example.btlwebbook.dto.request.BookDTO;
import ptit.example.btlwebbook.dto.request.OrderItemDTO;
import ptit.example.btlwebbook.model.Book;
import ptit.example.btlwebbook.model.CostInfo;
import ptit.example.btlwebbook.model.DeliveryInfo;
import ptit.example.btlwebbook.model.Tracking;
import ptit.example.btlwebbook.utils.OrderStatus;
import ptit.example.btlwebbook.utils.PaymentMethod;
import ptit.example.btlwebbook.utils.PaymentStatus;

import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponse {
    private Long id;
    private Long user;
    private DeliveryInfo delivery;
    private CostInfo cost;
    private PaymentMethod method;
    private PaymentStatus paymentStatus;
    private OrderStatus orderStatus;
    private String paymentId;
    private List<OrderItemResponse> orderDetails;
    private List<TrackingResponse> tracking;
    private String createdAt;
    private String updatedAt;
    private Long voucherId;
}
