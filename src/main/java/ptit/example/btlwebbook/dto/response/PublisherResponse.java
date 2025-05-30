package ptit.example.btlwebbook.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;
import net.minidev.json.annotate.JsonIgnore;
import ptit.example.btlwebbook.model.Book;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@Builder
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PublisherResponse {
    Long id;
    String name;
}
