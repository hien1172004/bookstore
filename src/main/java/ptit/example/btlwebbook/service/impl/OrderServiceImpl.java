package ptit.example.btlwebbook.service.impl;

import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import ptit.example.btlwebbook.dto.request.OrderDTO;
import ptit.example.btlwebbook.dto.request.TrackingDTO;
import ptit.example.btlwebbook.dto.response.OrderResponse;
import ptit.example.btlwebbook.dto.response.PageResponse;
import ptit.example.btlwebbook.exception.InvalidDataException;
import ptit.example.btlwebbook.exception.InvalidStatusException;
import ptit.example.btlwebbook.exception.ResourceNotFoundException;
import ptit.example.btlwebbook.mapper.OrderMapper;
import ptit.example.btlwebbook.model.*;
import ptit.example.btlwebbook.repository.*;
import ptit.example.btlwebbook.service.EmailService;
import ptit.example.btlwebbook.service.OrderService;
import ptit.example.btlwebbook.utils.OrderStatus;
import ptit.example.btlwebbook.utils.PaymentMethod;
import ptit.example.btlwebbook.utils.PaymentStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final UserRepository userRepository;
    private final TrackingRepository trackingRepository;
    private final VoucherRepository voucherRepository;
    private final EmailService emailService;
    private final BookRepository bookRepository;
    private final HttpServletRequest httpRequest;
    @Override
    @Transactional
    public OrderResponse createOrder(OrderDTO orderDTO) throws MessagingException {
        if(orderDTO.getOrderDetails() == null){
            throw new InvalidDataException("cart empty");
        }
        log.info(orderDTO.toString());
        User user = userRepository.findById(orderDTO.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + orderDTO.getUserId()));
        Order order = orderMapper.toOrder(orderDTO);
        order.setUser(user);
        if(orderDTO.getVoucherId() != null && orderDTO.getVoucherId() != 0){
            Voucher voucher = voucherRepository.findById(orderDTO.getVoucherId()).orElseThrow(() ->
                    new ResourceNotFoundException("voucher not found"));
            LocalDate now = LocalDate.now();
            if(orderDTO.getCost().getSubTotal() < voucher.getMinimum()){
                throw new InvalidDataException("The order does not meet the minimum order value requirement.");
            }
            if(now.isBefore(voucher.getStart()) || now.isAfter(voucher.getEnd())){
                throw new InvalidDataException("The timing is not suitable!");
            }
            order.setVoucher(voucher);
        }

        for (OrderDetail detail : order.getOrderDetails()) {
            Book book = bookRepository.findById(detail.getBook().getId()).orElseThrow(() ->
                    new ResourceNotFoundException("Book not found: " + detail.getBook().getId()));
            detail.setBook(book);
            detail.setOrder(order);// Cập nhật Book với dữ liệu đầy đủ
        }

        orderRepository.save(order);
//        OrderResponse orderResponse = orderMapper.toOrderResponse(order);
        log.info("saved");
        String origin = httpRequest.getHeader("Origin");
        log.info("get header");
        emailService.sendOrderSuccessEmail(order, origin);
        log.info("sent");

        return orderMapper.toOrderResponse(order);
    }

    @Override
    @Transactional
    public void updateOrder(long id, OrderDTO orderDTO) {
        Order order = orderRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("not found order"));
        orderMapper.update(order, orderDTO);
        orderRepository.save(order);
        log.info("order updated");
    }

    @Override
    @Transactional
    public OrderResponse getOrderById(long id) {
        Order order = orderRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("not found order"));
        return orderMapper.toOrderResponse(order);
    }

    @Override
    @Transactional
    public PageResponse<?> getAllOrder(int pageNo, int pageSize, Long userId, String sort) {
        int page = 0;
        if(pageNo > 0){
            page = pageNo - 1;
        }
        List<Sort.Order> sorts = new ArrayList<Sort.Order>();
        if(StringUtils.hasLength(sort)) {
            Pattern pattern = Pattern.compile("(\\w+?)(:)(.*)");
            Matcher matcher = pattern.matcher(sort);
            if (matcher.find()) {
                if (matcher.group(3).equalsIgnoreCase("asc")) {
                    sorts.add(new Sort.Order(Sort.Direction.ASC, matcher.group(1)));
                } else if (matcher.group(3).equalsIgnoreCase("desc")) {
                    sorts.add(new Sort.Order(Sort.Direction.DESC, matcher.group(1)));
                }
            }
        }
        Pageable pageable = PageRequest.of(page, pageSize, Sort.by(sorts));
        Page<Order> orders;
        if(userId != null && userId > 0){
            orders = orderRepository.findByUser_Id(userId, pageable);

        } else {
            orders = orderRepository.findAll(pageable);
        }
        List<OrderResponse> orderResponses = orders.stream().map(orderMapper::toOrderResponse).toList();
        return PageResponse.builder()
                .pageNo(pageNo)
                .pageSize(pageSize)
                .totalPages(orders.getTotalPages())
                .items(orderResponses)
                .build();
    }

    @Override
    public OrderResponse updatePaymentStatusByPaymentId(String paymentId, PaymentStatus paymentStatus, PaymentMethod method) {
        log.info("PaymentId---- {}", paymentId);
        Order order = orderRepository.findByPaymentId(paymentId).orElseThrow(()-> new ResourceNotFoundException("order not found"));
        order.setPaymentStatus(paymentStatus);
        order.setMethod(method);
        orderRepository.save(order);
        return orderMapper.toOrderResponse(order);
    }


    @Override
    @Transactional
    public OrderResponse updateStatus(long id, OrderStatus orderStatus) {
        Order order = orderRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("order not found"));
        int methodCode = order.getMethod().getCode();
        int oldPaymentStatusCode = order.getPaymentStatus().getCode();
        int oldCode = order.getOrderStatus().getCode();
        User user = order.getUser();
        // Kiểm tra thanh toán trước khi thay đổi trạng thái
        if (methodCode != PaymentMethod.CASH.getCode() && oldPaymentStatusCode != PaymentStatus.PAID.getCode()) {
            throw new InvalidStatusException("Khách hàng chưa thanh toán! Không thể thực hiện!");
        }
        if (orderStatus.getCode() <= oldCode) {
            throw new InvalidStatusException("Trạng thái không hợp lệ!");
        }

        if(methodCode == PaymentMethod.CASH.getCode() && orderStatus == OrderStatus.DELIVERED){
            order.setPaymentStatus(PaymentStatus.PAID);
        }
        order.setOrderStatus(orderStatus);
        orderRepository.save(order);
        Tracking tracking = Tracking.builder()
                .status(orderStatus)
                .user(user)
                .order(order)
                .build();
        trackingRepository.save(tracking);
        return orderMapper.toOrderResponse(order);
    }

    @Override
    public OrderResponse updatePaymentId(long id, String paymentId) {
        Order order = orderRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("not found order"));
        order.setPaymentId(paymentId);
        orderRepository.save(order);
        return orderMapper.toOrderResponse(order);
    }
    @Override
    public OrderResponse addTracking(Long id, TrackingDTO trackingDTO) {
        Order order = orderRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("not found order"));

        Tracking tracking = new Tracking();
        tracking.setStatus(trackingDTO.getStatus());
        User user = userRepository.findById(trackingDTO.getUserId()).orElseThrow(() -> new ResourceNotFoundException("not found user"));
        tracking.setUser(user);

        // Thêm tracking vào danh sách tracking của đơn hàng
        order.getTracking().add(tracking);

        // Cập nhật đơn hàng trong cơ sở dữ liệu
        orderRepository.save(order);
        return orderMapper.toOrderResponse(order);
        }

    @Override
    public PageResponse<?> getAllOrderOfUser(int pageNo, int pageSize, Long userId, String sort) {
        int page = 0;
        if(pageNo > 0){
            page = pageNo - 1;
        }
        List<Sort.Order> sorts = new ArrayList<Sort.Order>();
        if(StringUtils.hasLength(sort)) {
            Pattern pattern = Pattern.compile("(\\w+?)(:)(.*)");
            Matcher matcher = pattern.matcher(sort);
            if (matcher.find()) {
                if (matcher.group(3).equalsIgnoreCase("asc")) {
                    sorts.add(new Sort.Order(Sort.Direction.ASC, matcher.group(1)));
                } else if (matcher.group(3).equalsIgnoreCase("desc")) {
                    sorts.add(new Sort.Order(Sort.Direction.DESC, matcher.group(1)));
                }
            }
        }
        Pageable pageable = PageRequest.of(page, pageSize, Sort.by(sorts));
        Page<Order> orders = orderRepository.findByUser_Id(userId, pageable);
        List<OrderResponse> orderResponses = orders.stream().map(orderMapper::toOrderResponse).toList();
        return PageResponse.builder()
                .pageNo(pageNo)
                .pageSize(pageSize)
                .totalPages(orders.getTotalPages())
                .items(orderResponses)
                .build();
    }
}
