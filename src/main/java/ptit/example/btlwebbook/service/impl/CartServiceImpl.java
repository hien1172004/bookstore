package ptit.example.btlwebbook.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ptit.example.btlwebbook.dto.request.UpdateToCartDTO;
import ptit.example.btlwebbook.dto.response.CartResponse;
import ptit.example.btlwebbook.exception.ResourceNotFoundException;
import ptit.example.btlwebbook.mapper.CartMapper;
import ptit.example.btlwebbook.model.Book;
import ptit.example.btlwebbook.model.Cart;
import ptit.example.btlwebbook.model.CartItem;
import ptit.example.btlwebbook.model.User;
import ptit.example.btlwebbook.repository.BookRepository;
import ptit.example.btlwebbook.repository.CartItemRepository;
import ptit.example.btlwebbook.repository.CartRepository;
import ptit.example.btlwebbook.repository.UserRepository;
import ptit.example.btlwebbook.service.CartService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {
    private final BookRepository bookRepository;
    private final UserRepository userRepository;
    private final CartRepository cartRepository;
    private final CartMapper cartMapper;
    private final CartItemRepository cartItemRepository;

    @Transactional
    @Override
    public CartResponse addToCart(long userId, long bookId) {
        // Tìm người dùng
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy người dùng"));
        if (user.getId() == null) {
            throw new IllegalStateException("Người dùng không có ID hợp lệ");
        }

        // Tìm sách
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy sách"));
        if (book.getPrice() == null || book.getPrice() < 0) {
            throw new IllegalStateException("Giá sách không hợp lệ");
        }

        // Tìm giỏ hàng theo user_id
        Cart cart = cartRepository.findByUserId(user.getId())
                .orElseGet(() -> {
                    Cart newCart = Cart.builder().user(user).build();
                    newCart.setCartItems(new ArrayList<>());
                    return cartRepository.save(newCart);
                });

        // Kiểm tra user trong cart
        if (cart.getUser() == null || cart.getUser().getId() == null) {
            cart.setUser(user);
            cart = cartRepository.save(cart);
        }

        // Tìm CartItem
        List<CartItem> cartItems = cart.getCartItems();
        Optional<CartItem> existingItem = cartItems.stream()
                .filter(item -> item.getBook().getId().equals(bookId))
                .findFirst();

        // Cập nhật hoặc thêm mới CartItem
        if (existingItem.isPresent()) {
            CartItem item = existingItem.get();
            item.setQuantity(item.getQuantity() + 1);
        } else {
            CartItem newItem = CartItem.builder()
                    .book(book)
                    .cart(cart)
                    .quantity(1)
                    .build();
            cartItems.add(newItem);
            newItem.setCart(cart); // Đồng bộ mối quan hệ
        }

        // Tính tổng giá
        double totalPrice = cartItems.stream()
                .mapToDouble(item -> item.getBook().getPrice() * item.getQuantity())
                .sum();
        if (cart.getUser() == null || cart.getUser().getId() == null) {
            throw new IllegalStateException("Giỏ hàng không có người dùng hợp lệ");
        }
        // Lưu giỏ hàng
        Cart savedCart = cartRepository.save(cart);

        // Trả về phản hồi
        return new CartResponse(
                cart.getId(),
                cartItems.stream().map(cartMapper::toCartItemResponse).collect(Collectors.toList()),
                totalPrice
        );
    }



    @Override
    @Transactional
    public CartResponse updateCart(long userId, UpdateToCartDTO updateToCartDTO) {
        // Tìm người dùng
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy người dùng"));
        if (user.getId() == null) {
            throw new IllegalStateException("Người dùng không có ID hợp lệ");
        }

        // Tìm sách
//        Book book = bookRepository.findById(updateToCartDTO.getBookId())
//                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy sách"));

        // Tìm hoặc tạo giỏ hàng
        Cart cart = cartRepository.findByUserId(user.getId())
                .orElseGet(() -> {
                    Cart newCart = Cart.builder().user(user).build();
                    newCart.setCartItems(new ArrayList<>());
                    Cart savedCart = cartRepository.save(newCart); // Lưu Cart mới
                    user.setCart(savedCart); // Đồng bộ mối quan hệ
                    userRepository.save(user); // Lưu User
                    return savedCart;
                });

        // Tìm và cập nhật CartItem
        List<CartItem> cartItems = cart.getCartItems();
        Optional<CartItem> cartItem = cartItems.stream()
                .filter(item -> item.getBook().getId().equals(updateToCartDTO.getBookId()))
                .findFirst();

        if (cartItem.isPresent()) {
            CartItem item = cartItem.get();
            if (updateToCartDTO.getQuantity() > 0) {
                item.setQuantity(updateToCartDTO.getQuantity()); // Cập nhật số lượng
            } else {
                throw new IllegalArgumentException("Số lượng phải lớn hơn 0");
            }
        } else {
            throw new ResourceNotFoundException("Sản phẩm không tồn tại trong giỏ hàng");
        }

        // Lưu giỏ hàng
        Cart saved = cartRepository.save(cart);

        // Trả về phản hồi
        return cartMapper.toCartResponse(saved);
    }

        @Override
        @Transactional
        public void removeToCart(long userId, long bookId) {
            // Tìm người dùng
            User user = userRepository.findById(userId)
                    .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy người dùng"));
            if (user.getId() == null) {
                throw new IllegalStateException("Người dùng không có ID hợp lệ");
            }

            // Tìm sách
            Book book = bookRepository.findById(bookId)
                    .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy sách"));

            // Tìm giỏ hàng
            Cart cart = cartRepository.findByUserId(user.getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Giỏ hàng không tồn tại cho người dùng"));

            // Tìm và xóa CartItem
            List<CartItem> cartItems = cart.getCartItems();
            boolean removed = cartItems.removeIf(item -> item.getBook().getId().equals(bookId));

            // Nếu không tìm thấy sản phẩm, ném ngoại lệ
            if (!removed) {
                throw new ResourceNotFoundException("Không tìm thấy sản phẩm trong giỏ hàng");
            }

            // Lưu giỏ hàng
            cartRepository.save(cart);
        }

    @Override
    @Transactional
    public CartResponse getCart(long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Không tìm thấy người dùng"));

        Cart cart = cartRepository.findByUserId(user.getId())
                .orElseGet(() -> {
                    Cart newCart = Cart.builder().user(user).build();
                    newCart.setCartItems(new ArrayList<>());
                    return cartRepository.save(newCart);
                });
        List<CartItem> cartItems = cart.getCartItems();
        double totalPrice = cartItems.stream()
                .mapToDouble(item -> item.getBook().getPrice() * item.getQuantity())
                .sum();
        return new CartResponse(
                cart.getId(),
                cartItems.stream().map(cartMapper::toCartItemResponse).collect(Collectors.toList()),
                totalPrice
        );  // Trả về giỏ hàng của người dùng
    }
}
