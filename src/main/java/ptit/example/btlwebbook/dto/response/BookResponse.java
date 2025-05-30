package ptit.example.btlwebbook.dto.response;

import lombok.*;
import ptit.example.btlwebbook.model.Author;
import ptit.example.btlwebbook.model.Genre;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookResponse {
    private Long id;
    private String name;
    private String code;
    private String slug;
    private Integer year;
    private String description;
    private Integer pages;
    private String size;
    private Double price;
    private Double discount;
    private String imageUrl;
    private PublisherResponse publisher;            // Tên nhà xuất bản
    private List<AuthorResponse> authors;        // Danh sách tên tác giả
    private List<GenreResponse> genres;         // Danh sách tên thể loại
}
