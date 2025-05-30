package ptit.example.btlwebbook.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ptit.example.btlwebbook.model.Address;

import java.util.List;
import java.util.Optional;

public interface AddressRepository extends JpaRepository<Address, Long> {

    List<Address> findByUser_Id(Long userId);
    @Query("select a from Address a where a.user.id = :id and a.isDefault =true ")
    Optional<Address> findByUser_IdAndIsDefaultIsTrue(@Param("id") Long userId);
}
