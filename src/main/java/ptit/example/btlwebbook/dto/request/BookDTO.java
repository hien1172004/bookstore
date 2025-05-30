package ptit.example.btlwebbook.dto.request;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ptit.example.btlwebbook.model.*;

import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookDTO {
    @NotBlank(message = "Book name cannot be blank")
    private String name;

    @NotBlank(message = "Book code cannot be blank")
    @Size(min = 3, max = 20, message = "Book code must be between 3 and 20 characters")
    private String code;

    @NotNull(message = "Publication year cannot be null")
    private Integer year;

    private String description;

    @NotNull(message = "Number of pages cannot be null")
    @Positive(message = "Number of pages must be greater than 0")
    private Integer pages;

    private String size;

    private String imageUrl;

    private String publicId;
    @NotNull(message = "Price cannot be null")
    @Positive(message = "Price must be greater than 0")
    private Double price;

    private Double discount;

    @NotNull(message = "Publisher name cannot be blank")
    private PublisherDTO publisher;

    @NotNull(message = "Author names cannot be null")
    private List<AuthorDTO> authors;

    @NotNull(message = "Genre names cannot be null")
    private List<GenreDTO> genres;
    


}
