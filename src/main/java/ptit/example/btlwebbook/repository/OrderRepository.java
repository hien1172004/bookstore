package ptit.example.btlwebbook.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ptit.example.btlwebbook.dto.response.BestSellerResponse;
import ptit.example.btlwebbook.dto.response.OrderCountResponse;
import ptit.example.btlwebbook.dto.response.RevenueResponse;
import ptit.example.btlwebbook.model.Order;
import ptit.example.btlwebbook.utils.PaymentStatus;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    Page<Order> findByUser_Id(Long userId, Pageable pageable);

    Optional<Order> findByPaymentId(String paymentId);

    // Tổng doanh thu
    @Query(value = "SELECT COALESCE(SUM(o.total - o.shipping_fee), 0.0) " +
            "FROM orders o", nativeQuery = true)
    Double getTotalRevenue();

    // Doanh thu theo tuần
    @Query(value = "SELECT DATE_FORMAT(o.created_at, '%d-%m-%Y') AS date, COALESCE(SUM(o.total - o.shipping_fee), 0.0) AS revenue " +
            "FROM orders o WHERE o.created_at >= :start and o.created_at <= :end "+
            "GROUP BY DATE_FORMAT(o.created_at, '%d-%m-%Y') " +
            "ORDER BY DATE_FORMAT(o.created_at, '%d-%m-%Y') ", nativeQuery = true)
    List<RevenueResponse> getTotalRevenueByWeek(@Param("start") LocalDate start, @Param("end") LocalDate end);

    // Doanh thu toàn thời gian
    @Query(value = "SELECT DATE_FORMAT(o.created_at, '%d-%m-%Y') AS date, COALESCE(SUM(o.total - o.shipping_fee), 0.0) AS revenue " +
            "FROM orders o GROUP BY DATE_FORMAT(o.created_at, '%d-%m-%Y') " +
            "ORDER BY DATE_FORMAT(o.created_at, '%d-%m-%Y')", nativeQuery = true)
    List<RevenueResponse> getRevenueLifeTime();

//     Số lượng đơn hàng toàn thời gian
    @Query(value = "SELECT DATE_FORMAT(o.created_at, '%d-%m-%Y') AS date, COUNT(o.id) AS quantity " +
            "FROM orders o GROUP BY DATE_FORMAT(o.created_at, '%d-%m-%Y') " +
            "ORDER BY DATE_FORMAT(o.created_at, '%d-%m-%Y')", nativeQuery = true)
    List<Object[]> getOrderCountLifeTime();
//    @Query("SELECT new ptit.example.btlwebbook.dto.response.OrderCountResponse(" +
//            "CAST(FUNCTION('DATE_FORMAT', o.createdAt, '%d-%m-%Y') AS string), " +
//            "COUNT(o.id)) " +
//            "FROM Order o " +
//            "GROUP BY FUNCTION('DATE_FORMAT', o.createdAt, '%d-%m-%Y') " +
//            "ORDER BY FUNCTION('DATE_FORMAT', o.createdAt, '%d-%m-%Y')")
//    List<OrderCountResponse> getOrderCountLifeTime();
    // Sách bán chạy
    @Query(value = "SELECT b.id, b.name, SUM(od.quantity) AS quantity " +
            "FROM orders o " +
            "JOIN order_detail od ON o.id = od.order_id " +
            "JOIN book b ON od.book_id = b.id " +
            "GROUP BY b.id, b.name " +
            "ORDER BY quantity DESC " +
            "LIMIT 5", nativeQuery = true)
    List<Object[]> getBestSeller();
}
