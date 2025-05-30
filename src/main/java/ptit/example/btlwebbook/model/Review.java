package ptit.example.btlwebbook.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class Review extends AbstractEntity implements Serializable {
    @Column(name = "content", nullable = false)
    private String content;
    @Column(name = "rating") // Thêm trường rating nếu cần
    private Integer rating;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;
}
