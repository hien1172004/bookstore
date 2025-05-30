package ptit.example.btlwebbook.dto.request;

import lombok.Getter;
import lombok.Setter;
import ptit.example.btlwebbook.contraints.EnumPattern;
import ptit.example.btlwebbook.utils.OrderStatus;
import ptit.example.btlwebbook.utils.PaymentMethod;
import ptit.example.btlwebbook.utils.PaymentStatus;
@Getter
@Setter
public class UpdateOrderDTO {
    @EnumPattern(name = "orderStatus", regexp = "AWAITING_CHECK_PAYMENT|PAYMENT_ACCEPTED|READY_TO_SHIP|TRANSIT|PICKUP|DELIVERED")
    private OrderStatus orderStatus;
}