package ptit.example.btlwebbook.mapper;

import jakarta.persistence.ManyToMany;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ptit.example.btlwebbook.dto.response.CartItemResponse;
import ptit.example.btlwebbook.dto.response.CartResponse;
import ptit.example.btlwebbook.model.Cart;
import ptit.example.btlwebbook.model.CartItem;

@Mapper(componentModel = "spring")
public interface CartMapper {
        @Mapping(source = "book.id", target = "bookId")
        CartItemResponse toCartItemResponse(CartItem cartItem);
        @Mapping(source = "cartItems", target = "items")
        CartResponse toCartResponse(Cart cart);
}
