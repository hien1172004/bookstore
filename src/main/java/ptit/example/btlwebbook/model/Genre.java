package ptit.example.btlwebbook.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import net.minidev.json.annotate.JsonIgnore;

import java.util.List;

@Entity
@Table(name = "Genre", indexes = {
        @Index(name = "idx_genre_name", columnList = "name")
})
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class Genre extends AbstractEntity{
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String slug;
    @ManyToMany(mappedBy = "genres")
    private List<Book> books;
}
