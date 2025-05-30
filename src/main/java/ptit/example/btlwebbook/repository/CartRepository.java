package ptit.example.btlwebbook.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ptit.example.btlwebbook.model.Cart;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long> {
    Optional<Cart> findByUserId(Long id);
}
