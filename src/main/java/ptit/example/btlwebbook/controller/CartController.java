package ptit.example.btlwebbook.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ptit.example.btlwebbook.dto.request.UpdateToCartDTO;
import ptit.example.btlwebbook.dto.response.BookResponse;
import ptit.example.btlwebbook.dto.response.CartResponse;
import ptit.example.btlwebbook.dto.response.ResponeError;
import ptit.example.btlwebbook.dto.response.ResponseData;
import ptit.example.btlwebbook.service.CartService;

@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping("/users")
public class CartController {
    String ERROR_MESSAGE ="errorMessage: {} ";
    private final CartService cartService;
    @PostMapping("/{userId}/addToCart")
    @PreAuthorize("#userId == authentication.principal.id or hasRole('ADMIN')")
    public ResponseData<?> addToCart(@PathVariable long userId, @RequestBody long bookId){
        try {
            CartResponse cartResponse = cartService.addToCart(userId, bookId);
            return new ResponseData<>(HttpStatus.CREATED.value(), "success", cartResponse);
        }catch (Exception e){
            log.error(ERROR_MESSAGE, e.getMessage(), e.getCause());
            return new ResponeError(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        }
    }

    @PutMapping("/{userId}/cart")
    @PreAuthorize("#userId == authentication.principal.id or hasRole('ADMIN')")
    public ResponseData<?> updateCart(@PathVariable long userId, @RequestBody @Valid UpdateToCartDTO updateToCartDTO){
        try {
            CartResponse cartResponse = cartService.updateCart(userId, updateToCartDTO);
            return new ResponseData<>(HttpStatus.ACCEPTED.value(), "success", cartResponse);
        }catch (Exception e){
            log.error(ERROR_MESSAGE, e.getMessage(), e.getCause());
            return new ResponeError(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        }
    }

    @GetMapping("{userId}/cart")
    @PreAuthorize("#userId == authentication.principal.id or hasAnyRole('STAFF', 'ADMIN')")
    public ResponseData<?> getCart(@PathVariable long userId){
        try {
            CartResponse cartResponse = cartService.getCart(userId);
            return new ResponseData<>(HttpStatus.OK.value(), "success", cartResponse);
        }catch (Exception e){
            log.error(ERROR_MESSAGE, e.getMessage(), e.getCause());
            return new ResponeError(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        }
    }

    @DeleteMapping("/{userId}/cart/{bookId}")
    @PreAuthorize("#userId == authentication.principal.id or hasAnyRole('STAFF', 'ADMIN')")
    public  ResponseData<Void> deleteItemInCart(@PathVariable long userId, @PathVariable long bookId){
        try {
            cartService.removeToCart(userId, bookId);
            return new ResponseData<>(HttpStatus.NO_CONTENT.value(), "success");
        }catch (Exception e){
            log.error(ERROR_MESSAGE, e.getMessage(), e.getCause());
            return new ResponeError(HttpStatus.BAD_REQUEST.value(), e.getMessage());
        }
    }
}
