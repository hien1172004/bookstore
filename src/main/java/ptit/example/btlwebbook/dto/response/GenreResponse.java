package ptit.example.btlwebbook.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class GenreResponse {
    Long id;
    String name;
    String slug;
}
