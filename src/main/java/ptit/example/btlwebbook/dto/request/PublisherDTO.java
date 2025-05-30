package ptit.example.btlwebbook.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PublisherDTO {
    @NotBlank(message = "name is required")
    private String name;
}
