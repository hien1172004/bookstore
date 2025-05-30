package ptit.example.btlwebbook.service;

import ptit.example.btlwebbook.dto.request.UpdateToCartDTO;
import ptit.example.btlwebbook.dto.response.CartResponse;

public interface CartService {
    CartResponse addToCart(long userId, long bookId);

    CartResponse updateCart(long userId, UpdateToCartDTO  updateToCartDTO);

    void removeToCart(long userId, long bookId);

    CartResponse getCart(long useId);
}
