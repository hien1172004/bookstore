package ptit.example.btlwebbook.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "Book", indexes = {
        @Index(name = "idx_book_name_publisher_id", columnList = "name, publisher_id"),
        @Index(name = "idx_book_created_at", columnList = "created_at")
})
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class Book extends AbstractEntity{
    @Column(nullable = false)
    private String name;
    @Column(nullable = false, unique = true)
    private String slug;
    @Column(name = "code", nullable = false, unique = true)
    private String code;
    private Integer year;
    @ManyToMany
   @JoinTable(
           name = "book_genre",
           joinColumns = @JoinColumn(name = "book_id"),
           inverseJoinColumns = @JoinColumn(name = "genre_id")
   )
    private Set<Genre> genres;
    @ManyToMany
    @JoinTable(
            name = "book_authors",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id")
    )
    private Set<Author> authors; // Liên kết với bảng tác giả
    @ManyToOne
    @JoinColumn(name = "publisher_id")
    private Publisher publisher; // Liên kết với nhà xuất bản

    @Column(columnDefinition = "TEXT")
    private String description;

    private Integer pages;
    private String size;

    @Column(nullable = false)
    private Double price;

    private Double discount;
    @Builder.Default
    private String imageUrl = "https://itbook.store/img/books/9781617294136.png";

    private String publicId;

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CartItem> cartItems;

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Review> reviews = new ArrayList<>();

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderDetail> orderDetails;
}
