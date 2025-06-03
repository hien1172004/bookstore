package ptit.example.btlwebbook.dto.response;

import lombok.*;
import net.minidev.json.annotate.JsonIgnore;
import ptit.example.btlwebbook.model.Book;

import java.util.Date;
import java.util.List;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthorResponse {
    private Long id;
    private String name;
}

