package ptit.example.btlwebbook.repository;

import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ptit.example.btlwebbook.model.Genre;

import java.util.List;

@Repository
public interface GenreRespository extends JpaRepository<Genre, Long> {
    Genre findBySlug(String slug);

    List<Genre> findByNameIn(List<String> list);

    Genre findByName(@NotBlank(message = "name is required") String name);
}
