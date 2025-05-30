package ptit.example.btlwebbook.mapper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import ptit.example.btlwebbook.dto.request.CostInfoDTO;
import ptit.example.btlwebbook.dto.request.DeliveryInfoDTO;
import ptit.example.btlwebbook.dto.request.OrderDTO;
import ptit.example.btlwebbook.dto.request.OrderItemDTO;
import ptit.example.btlwebbook.dto.response.OrderItemResponse;
import ptit.example.btlwebbook.dto.response.OrderResponse;
import ptit.example.btlwebbook.dto.response.TrackingResponse;
import ptit.example.btlwebbook.model.Book;
import ptit.example.btlwebbook.model.CostInfo;
import ptit.example.btlwebbook.model.DeliveryInfo;
import ptit.example.btlwebbook.model.Order;
import ptit.example.btlwebbook.model.OrderDetail;
import ptit.example.btlwebbook.model.Tracking;
import ptit.example.btlwebbook.model.User;
import ptit.example.btlwebbook.model.Voucher;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-31T00:54:34+0700",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.42.0.v20250514-1000, environment: Java 21.0.7 (Eclipse Adoptium)"
)
@Component
public class OrderMapperImpl implements OrderMapper {

    @Override
    public Order toOrder(OrderDTO orderDTO) {
        if ( orderDTO == null ) {
            return null;
        }

        Order.OrderBuilder<?, ?> order = Order.builder();

        order.user( orderDTOToUser( orderDTO ) );
        order.cost( toCostInfo( orderDTO.getCost() ) );
        order.delivery( toDelivery( orderDTO.getDelivery() ) );
        order.method( orderDTO.getMethod() );
        order.orderDetails( orderItemDTOListToOrderDetailList( orderDTO.getOrderDetails() ) );
        order.paymentId( orderDTO.getPaymentId() );

        return order.build();
    }

    @Override
    public OrderResponse toOrderResponse(Order order) {
        if ( order == null ) {
            return null;
        }

        OrderResponse orderResponse = new OrderResponse();

        orderResponse.setVoucherId( orderVoucherId( order ) );
        orderResponse.setUser( orderUserId( order ) );
        orderResponse.setCost( order.getCost() );
        if ( order.getCreatedAt() != null ) {
            orderResponse.setCreatedAt( new SimpleDateFormat().format( order.getCreatedAt() ) );
        }
        orderResponse.setDelivery( order.getDelivery() );
        orderResponse.setId( order.getId() );
        orderResponse.setMethod( order.getMethod() );
        orderResponse.setOrderDetails( orderDetailListToOrderItemResponseList( order.getOrderDetails() ) );
        orderResponse.setOrderStatus( order.getOrderStatus() );
        orderResponse.setPaymentId( order.getPaymentId() );
        orderResponse.setPaymentStatus( order.getPaymentStatus() );
        orderResponse.setTracking( trackingListToTrackingResponseList( order.getTracking() ) );
        if ( order.getUpdatedAt() != null ) {
            orderResponse.setUpdatedAt( new SimpleDateFormat().format( order.getUpdatedAt() ) );
        }

        return orderResponse;
    }

    @Override
    public void update(Order order, OrderDTO orderDTO) {
        if ( orderDTO == null ) {
            return;
        }

        order.setCost( toCostInfo( orderDTO.getCost() ) );
        order.setDelivery( toDelivery( orderDTO.getDelivery() ) );
        order.setMethod( orderDTO.getMethod() );
        if ( order.getOrderDetails() != null ) {
            List<OrderDetail> list = orderItemDTOListToOrderDetailList( orderDTO.getOrderDetails() );
            if ( list != null ) {
                order.getOrderDetails().clear();
                order.getOrderDetails().addAll( list );
            }
            else {
                order.setOrderDetails( null );
            }
        }
        else {
            List<OrderDetail> list = orderItemDTOListToOrderDetailList( orderDTO.getOrderDetails() );
            if ( list != null ) {
                order.setOrderDetails( list );
            }
        }
        order.setPaymentId( orderDTO.getPaymentId() );
    }

    @Override
    public OrderDetail toOrderDetail(OrderItemDTO orderItemDTO) {
        if ( orderItemDTO == null ) {
            return null;
        }

        OrderDetail.OrderDetailBuilder<?, ?> orderDetail = OrderDetail.builder();

        orderDetail.book( orderItemDTOToBook( orderItemDTO ) );
        orderDetail.price( orderItemDTO.getPrice() );
        orderDetail.quantity( orderItemDTO.getQuantity() );
        orderDetail.totalItem( orderItemDTO.getTotalItem() );

        return orderDetail.build();
    }

    @Override
    public OrderItemResponse toOrderItemResponse(OrderDetail orderDetail) {
        if ( orderDetail == null ) {
            return null;
        }

        OrderItemResponse orderItemResponse = new OrderItemResponse();

        orderItemResponse.setBookId( orderDetailBookId( orderDetail ) );
        orderItemResponse.setPrice( orderDetail.getPrice() );
        orderItemResponse.setQuantity( orderDetail.getQuantity() );
        orderItemResponse.setTotalItem( orderDetail.getTotalItem() );

        return orderItemResponse;
    }

    @Override
    public DeliveryInfo toDelivery(DeliveryInfoDTO deliveryInfoDTO) {
        if ( deliveryInfoDTO == null ) {
            return null;
        }

        DeliveryInfo deliveryInfo = new DeliveryInfo();

        deliveryInfo.setAddress( deliveryInfoDTO.getAddress() );
        deliveryInfo.setEmail( deliveryInfoDTO.getEmail() );
        deliveryInfo.setFullName( deliveryInfoDTO.getFullName() );
        deliveryInfo.setPhoneNumber( deliveryInfoDTO.getPhoneNumber() );

        return deliveryInfo;
    }

    @Override
    public CostInfo toCostInfo(CostInfoDTO costInfoDTO) {
        if ( costInfoDTO == null ) {
            return null;
        }

        CostInfo costInfo = new CostInfo();

        costInfo.setDiscount( costInfoDTO.getDiscount() );
        costInfo.setShippingFee( costInfoDTO.getShippingFee() );
        costInfo.setSubTotal( costInfoDTO.getSubTotal() );
        costInfo.setTotal( costInfoDTO.getTotal() );

        return costInfo;
    }

    protected User orderDTOToUser(OrderDTO orderDTO) {
        if ( orderDTO == null ) {
            return null;
        }

        User.UserBuilder<?, ?> user = User.builder();

        user.id( orderDTO.getUserId() );

        return user.build();
    }

    protected List<OrderDetail> orderItemDTOListToOrderDetailList(List<OrderItemDTO> list) {
        if ( list == null ) {
            return null;
        }

        List<OrderDetail> list1 = new ArrayList<OrderDetail>( list.size() );
        for ( OrderItemDTO orderItemDTO : list ) {
            list1.add( toOrderDetail( orderItemDTO ) );
        }

        return list1;
    }

    private Long orderVoucherId(Order order) {
        if ( order == null ) {
            return null;
        }
        Voucher voucher = order.getVoucher();
        if ( voucher == null ) {
            return null;
        }
        Long id = voucher.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Long orderUserId(Order order) {
        if ( order == null ) {
            return null;
        }
        User user = order.getUser();
        if ( user == null ) {
            return null;
        }
        Long id = user.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    protected List<OrderItemResponse> orderDetailListToOrderItemResponseList(List<OrderDetail> list) {
        if ( list == null ) {
            return null;
        }

        List<OrderItemResponse> list1 = new ArrayList<OrderItemResponse>( list.size() );
        for ( OrderDetail orderDetail : list ) {
            list1.add( toOrderItemResponse( orderDetail ) );
        }

        return list1;
    }

    protected TrackingResponse trackingToTrackingResponse(Tracking tracking) {
        if ( tracking == null ) {
            return null;
        }

        TrackingResponse trackingResponse = new TrackingResponse();

        trackingResponse.setCreatedAt( tracking.getCreatedAt() );
        trackingResponse.setStatus( tracking.getStatus() );

        return trackingResponse;
    }

    protected List<TrackingResponse> trackingListToTrackingResponseList(List<Tracking> list) {
        if ( list == null ) {
            return null;
        }

        List<TrackingResponse> list1 = new ArrayList<TrackingResponse>( list.size() );
        for ( Tracking tracking : list ) {
            list1.add( trackingToTrackingResponse( tracking ) );
        }

        return list1;
    }

    protected Book orderItemDTOToBook(OrderItemDTO orderItemDTO) {
        if ( orderItemDTO == null ) {
            return null;
        }

        Book.BookBuilder<?, ?> book = Book.builder();

        book.id( orderItemDTO.getBookId() );

        return book.build();
    }

    private Long orderDetailBookId(OrderDetail orderDetail) {
        if ( orderDetail == null ) {
            return null;
        }
        Book book = orderDetail.getBook();
        if ( book == null ) {
            return null;
        }
        Long id = book.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
