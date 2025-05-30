package ptit.example.btlwebbook.service;

import jakarta.mail.MessagingException;
import ptit.example.btlwebbook.dto.request.OrderDTO;
import ptit.example.btlwebbook.dto.request.TrackingDTO;
import ptit.example.btlwebbook.dto.response.OrderResponse;
import ptit.example.btlwebbook.dto.response.PageResponse;
import ptit.example.btlwebbook.model.Order;
import ptit.example.btlwebbook.utils.OrderStatus;
import ptit.example.btlwebbook.utils.PaymentMethod;
import ptit.example.btlwebbook.utils.PaymentStatus;

public interface OrderService {
    OrderResponse createOrder(OrderDTO orderDTO) throws MessagingException;

    void updateOrder(long id, OrderDTO orderDTO);

    OrderResponse getOrderById(long id);
    PageResponse<?> getAllOrder(int pageNo, int pageSize, Long userId , String sort);

    OrderResponse updatePaymentStatusByPaymentId(String paymentId, PaymentStatus paymentStatus, PaymentMethod method);

    OrderResponse updateStatus(long id, OrderStatus orderStatus);

    OrderResponse updatePaymentId(long id, String paymentId);

    OrderResponse addTracking(Long id, TrackingDTO trackingDTO);

    PageResponse<?> getAllOrderOfUser(int pageNo, int pageSize, Long userId , String sort);
}
