package ptit.example.btlwebbook.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignUpDTO {
    @NotBlank(message = "email must not blank")
    @Email(message = "email invalid format")
    private String email;
    @NotBlank(message = "fullName must not blank")
    private String fullName;
    @NotBlank(message = "password must not blank")
    private String password;
    @NotBlank(message = "confirm password must not blank")
    private String confirmPassword;
}
