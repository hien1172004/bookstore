package ptit.example.btlwebbook.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import ptit.example.btlwebbook.utils.PaymentMethod;


import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {
    private Long userId;
    private DeliveryInfoDTO delivery;
    private CostInfoDTO cost;
    private PaymentMethod method;
    private String paymentId;
    private List<OrderItemDTO> orderDetails;
    private Long voucherId;
}
