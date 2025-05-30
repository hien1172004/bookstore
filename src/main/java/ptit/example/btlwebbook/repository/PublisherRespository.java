package ptit.example.btlwebbook.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ptit.example.btlwebbook.model.Publisher;
@Repository
public interface PublisherRespository extends JpaRepository<Publisher, Long> {
    Publisher findByName(String name);
}
