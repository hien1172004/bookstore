package ptit.example.btlwebbook.dto.response;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PageResponse<T>  {
    private int pageNo;
    private int pageSize;
    private int totalPages;
    private T items;
}
