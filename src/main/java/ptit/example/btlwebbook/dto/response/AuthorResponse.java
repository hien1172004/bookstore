package ptit.example.btlwebbook.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import net.minidev.json.annotate.JsonIgnore;
import ptit.example.btlwebbook.model.Book;

import java.util.Date;
import java.util.List;

@Setter
@Getter
@Builder
public class AuthorResponse {
    private Long id;
    private String name;
}

