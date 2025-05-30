package ptit.example.btlwebbook.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ptit.example.btlwebbook.dto.request.OrderDTO;
import ptit.example.btlwebbook.dto.request.UpdateOrderDTO;
import ptit.example.btlwebbook.dto.response.*;
import ptit.example.btlwebbook.model.Order;
import ptit.example.btlwebbook.service.OrderService;

@RestController
@RequestMapping ("/orders")
@Validated
@RequiredArgsConstructor
@Slf4j
public class OrderController {
    private final OrderService orderService;
    public final String ERROR_MESSAGE ="errorMessage: {} ";
    @PostMapping("/")
    @PreAuthorize("isAuthenticated()")
    public ResponseData<OrderResponse> creatOrder(@Valid @RequestBody OrderDTO orderDTO){
        try {
            OrderResponse response = orderService.createOrder(orderDTO);
            return new ResponseData<>(HttpStatus.CREATED.value(), "success", response);
        }catch (Exception e){
            log.error(ERROR_MESSAGE, e.getMessage(), e.getCause());
            return new ResponeError(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        }
    }
    

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'STAFF', 'USER')")
    public ResponseData<OrderResponse> getOrderById(@PathVariable long id){
        try {
            OrderResponse response = orderService.getOrderById(id);
            return new ResponseData<>(HttpStatus.OK.value(), "success", response);
        }catch (Exception e){
            log.error(ERROR_MESSAGE, e.getMessage(), e.getCause());
            return new ResponeError(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        }
    }

    @GetMapping("/")
    @PreAuthorize("hasAnyRole('ADMIN', 'STAFF')")
    public ResponseData<?> getAllOrders(@RequestParam(defaultValue = "1", required = false) int pageNo,
                                                    @RequestParam(defaultValue = "8", required = false) int pageSize,
                                                    @RequestParam(defaultValue = "createdAt:desc", required = false) String sortBy,
                                                    @RequestParam(required = false) Long userId){
        try {
            PageResponse<?> response = orderService.getAllOrder(pageNo, pageSize, userId, sortBy);
            return new ResponseData<>(HttpStatus.OK.value(), "success", response);
        }catch (Exception e){
            log.error(ERROR_MESSAGE, e.getMessage(), e.getCause());
            return new ResponeError(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        }
    }
    @GetMapping("/user")
    public ResponseData<?> getALlOrderOfUser(@RequestParam(defaultValue = "1", required = false) int pageNo,
                                             @RequestParam(defaultValue = "8", required = false) int pageSize,
                                             @RequestParam(defaultValue = "createdAt:desc", required = false) String sortBy,
                                             @RequestParam(required = true) Long userId){
        try {
            PageResponse<?> response = orderService.getAllOrder(pageNo, pageSize, userId, sortBy);
            return new ResponseData<>(HttpStatus.OK.value(), "success", response);
        }catch (Exception e){
            log.error(ERROR_MESSAGE, e.getMessage(), e.getCause());
            return new ResponeError(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        }
    }

    @PutMapping("/{id}/order-status")
    @PreAuthorize("hasAnyRole('ADMIN', 'STAFF')")
    public ResponseData<?> updateOrderStatus(@PathVariable Long id, @Valid @RequestBody UpdateOrderDTO updateOrderDTO){
        try {
            OrderResponse orderResponse = orderService.updateStatus(id, updateOrderDTO.getOrderStatus());
            return new ResponseData<>(HttpStatus.ACCEPTED.value(), "success", orderResponse);
        }catch (Exception e){
            log.error(ERROR_MESSAGE, e.getMessage(), e.getCause());
            return new ResponeError(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        }

    }

    @PutMapping("/{id}/paymentId")
    @PreAuthorize("hasAnyRole('ADMIN', 'STAFF', 'USER')")
    public ResponseData<?> updatePaymentId(@PathVariable long id, @Valid @RequestBody String paymentId ){
        try {
            OrderResponse orderResponse = orderService.updatePaymentId(id, paymentId);
            return new ResponseData<>(HttpStatus.ACCEPTED.value(), "success", orderResponse);
        }catch (Exception e){
            log.error(ERROR_MESSAGE, e.getMessage(), e.getCause());
            return new ResponeError(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        }

    }
}
