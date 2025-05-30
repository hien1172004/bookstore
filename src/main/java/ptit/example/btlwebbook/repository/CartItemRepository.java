package ptit.example.btlwebbook.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ptit.example.btlwebbook.model.CartItem;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
}
