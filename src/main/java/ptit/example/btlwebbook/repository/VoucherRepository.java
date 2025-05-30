package ptit.example.btlwebbook.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ptit.example.btlwebbook.model.Voucher;

public interface VoucherRepository extends JpaRepository<Voucher, Long> {
    Voucher findByCode(String code);
    @Query("select v from Voucher v where current_timestamp  between v.start and  v.end")
    Page<Voucher> findAllVoucherActive(Pageable pageable);
}
