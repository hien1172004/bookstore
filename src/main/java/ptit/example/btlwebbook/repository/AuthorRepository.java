package ptit.example.btlwebbook.repository;

import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ptit.example.btlwebbook.model.Author;
@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
    Author findByName(@NotBlank(message = "name is required") String name);
}
