package ptit.example.btlwebbook.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import lombok.*;
import lombok.experimental.SuperBuilder;
import net.minidev.json.annotate.JsonIgnore;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Author extends AbstractEntity {
  @Column(nullable = false)
  private String name;
  @Column(name = "birth_day")
  private Date birthDay;
  @ManyToMany(mappedBy = "authors")
  @JsonIgnore
  private List<Book> books;
}