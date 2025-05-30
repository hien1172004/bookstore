package ptit.example.btlwebbook.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserDTO {
    @NotBlank(message = "fullName must be not blank")
    private String fullName;
    @NotNull(message = "Gender cannot be null")
    private Integer gender;
    @NotNull(message = "dateOfBirth must be not null")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @JsonFormat(pattern = "MM/dd/yyyy")
    private String birthday;
    @NotBlank(message = "phoneNumber is not blank")
    private String phoneNumber;
}
