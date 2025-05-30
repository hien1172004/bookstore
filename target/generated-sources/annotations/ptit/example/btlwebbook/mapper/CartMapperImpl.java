package ptit.example.btlwebbook.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import ptit.example.btlwebbook.dto.response.CartItemResponse;
import ptit.example.btlwebbook.dto.response.CartResponse;
import ptit.example.btlwebbook.model.Book;
import ptit.example.btlwebbook.model.Cart;
import ptit.example.btlwebbook.model.CartItem;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-31T00:54:34+0700",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.42.0.v20250514-1000, environment: Java 21.0.7 (Eclipse Adoptium)"
)
@Component
public class CartMapperImpl implements CartMapper {

    @Override
    public CartItemResponse toCartItemResponse(CartItem cartItem) {
        if ( cartItem == null ) {
            return null;
        }

        CartItemResponse cartItemResponse = new CartItemResponse();

        Long id = cartItemBookId( cartItem );
        if ( id != null ) {
            cartItemResponse.setBookId( id );
        }
        cartItemResponse.setId( cartItem.getId() );
        cartItemResponse.setQuantity( cartItem.getQuantity() );

        return cartItemResponse;
    }

    @Override
    public CartResponse toCartResponse(Cart cart) {
        if ( cart == null ) {
            return null;
        }

        CartResponse.CartResponseBuilder cartResponse = CartResponse.builder();

        cartResponse.items( cartItemListToCartItemResponseList( cart.getCartItems() ) );
        if ( cart.getId() != null ) {
            cartResponse.id( cart.getId() );
        }

        return cartResponse.build();
    }

    private Long cartItemBookId(CartItem cartItem) {
        if ( cartItem == null ) {
            return null;
        }
        Book book = cartItem.getBook();
        if ( book == null ) {
            return null;
        }
        Long id = book.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    protected List<CartItemResponse> cartItemListToCartItemResponseList(List<CartItem> list) {
        if ( list == null ) {
            return null;
        }

        List<CartItemResponse> list1 = new ArrayList<CartItemResponse>( list.size() );
        for ( CartItem cartItem : list ) {
            list1.add( toCartItemResponse( cartItem ) );
        }

        return list1;
    }
}
