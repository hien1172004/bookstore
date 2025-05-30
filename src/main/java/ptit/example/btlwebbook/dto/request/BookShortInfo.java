package ptit.example.btlwebbook.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BookShortInfo {
    private Long id;
    private String name;
    private String imageUrl;
    private Double price;
    private Double discount;
}