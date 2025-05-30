package ptit.example.btlwebbook.mapper;

import lombok.Setter;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import ptit.example.btlwebbook.dto.request.CostInfoDTO;
import ptit.example.btlwebbook.dto.request.DeliveryInfoDTO;
import ptit.example.btlwebbook.dto.request.OrderDTO;
import ptit.example.btlwebbook.dto.request.OrderItemDTO;
import ptit.example.btlwebbook.dto.response.OrderItemResponse;
import ptit.example.btlwebbook.dto.response.OrderResponse;
import ptit.example.btlwebbook.model.CostInfo;
import ptit.example.btlwebbook.model.DeliveryInfo;
import ptit.example.btlwebbook.model.Order;
import ptit.example.btlwebbook.model.OrderDetail;

@Mapper(componentModel = "spring")
public interface OrderMapper {
//    @Mapping(source = "voucherId", target = "voucher.id")
    @Mapping(source = "userId", target = "user.id")
    Order toOrder(OrderDTO orderDTO);
    @Mapping(source = "voucher.id", target = "voucherId")
    @Mapping(source = "user.id", target = "user")
    OrderResponse toOrderResponse(Order order);
    void update(@MappingTarget Order order, OrderDTO orderDTO);
    @Mapping(source = "bookId", target = "book.id")
    OrderDetail toOrderDetail(OrderItemDTO orderItemDTO);

    @Mapping(source = "book.id", target = "bookId")
    OrderItemResponse toOrderItemResponse(OrderDetail orderDetail);

    DeliveryInfo toDelivery(DeliveryInfoDTO deliveryInfoDTO);

    CostInfo toCostInfo(CostInfoDTO costInfoDTO);

}
