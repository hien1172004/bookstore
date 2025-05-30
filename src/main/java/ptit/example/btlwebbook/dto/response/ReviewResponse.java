package ptit.example.btlwebbook.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReviewResponse {
    private Long id;
    private String content;
    private Integer rating;
    private Long userId;
    private Long bookId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
