package ptit.example.btlwebbook.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ptit.example.btlwebbook.model.Tracking;

public interface TrackingRepository extends JpaRepository<Tracking, Long> {
}
