package ptit.example.btlwebbook.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ptit.example.btlwebbook.model.Book;
import ptit.example.btlwebbook.model.Genre;

import java.util.List;
@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findBookByAuthors_Id(Long authorsId);

    List<Book> findByGenres_Id(Long genresId);

    List<Book> findAllByPublisher_Id(Long publisherId);

    Book findBySlug(String slug);

//    @EntityGraph(attributePaths = {"authors", "genres", "publisher"})
//    Page<Book> findAll(Pageable pageable);
//    @EntityGraph(attributePaths = {"authors", "genres", "publisher"})
    @Query("select distinct b from Book b join b.genres g where g.id in :genres")
    Page<Book> findByGenresIn(@Param("genres") List<Long> genres, Pageable pageable);

    @Query("SELECT DISTINCT b FROM Book b JOIN b.authors a " +
            "WHERE LOWER(a.name) LIKE LOWER(CONCAT('%', :key, '%')) " +
            "OR LOWER(b.name) LIKE LOWER(CONCAT('%', :key, '%'))")
    Page<Book> searchBooksByAuthorOrName(@Param("key") String key, Pageable pageable);

    boolean existsByName(String name);

    Book findByCode(String code);
}