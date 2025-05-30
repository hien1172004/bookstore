package ptit.example.btlwebbook.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ptit.example.btlwebbook.model.OrderDetail;

public interface OrderDetailRepository extends JpaRepository<OrderDetail,Long > {
    @Query("SELECT COUNT(oi) > 0 FROM OrderDetail oi WHERE oi.book.id = :bookId")
    boolean existsByBookId(@Param("bookId") Long bookId);
}
