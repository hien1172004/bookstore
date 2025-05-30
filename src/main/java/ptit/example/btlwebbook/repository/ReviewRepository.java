package ptit.example.btlwebbook.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ptit.example.btlwebbook.model.Review;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    boolean existsByBookIdAndUserId(Long book_id, Long user_id);

    Page<Review> findByBookId(long bookId, Pageable attr1);


    List<Review> findByUser_Id(Long userId);
}
