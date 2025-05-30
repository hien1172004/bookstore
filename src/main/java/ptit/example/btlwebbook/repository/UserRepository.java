package ptit.example.btlwebbook.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ptit.example.btlwebbook.model.User;
import ptit.example.btlwebbook.utils.ROLE;
import ptit.example.btlwebbook.utils.UserStatus;


import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);

    @Query("SELECT u FROM User u WHERE " +
            "(LOWER(u.fullName) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(u.email) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "u.phoneNumber LIKE CONCAT('%', :keyword, '%')) AND " +
            "u.role = ptit.example.btlwebbook.utils.ROLE.USER")
    Page<User> getUsersByQuery(@Param("keyword") String query, Pageable pageable);

    @Query("SELECT u FROM User u WHERE u.role = :role")
    Page<User> findByRole(@Param("role") ROLE  role, Pageable pageable);

    @Query("SELECT u FROM User u WHERE " +
            "(LOWER(u.fullName) LIKE LOWER(CONCAT('%', :query, '%')) OR " +
            "LOWER(u.email) LIKE LOWER(CONCAT('%', :query, '%')) OR " +
            "u.phoneNumber LIKE CONCAT('%', :query, '%')) AND " +
            "u.role = :role")
    Page<User> findByQueryAndRole(@Param("query") String query, @Param("role") ROLE role, Pageable pageable);

    List<User> findByStatusAndCreatedAtBefore(UserStatus userStatus, Date twentyFourHoursAgo);
}
