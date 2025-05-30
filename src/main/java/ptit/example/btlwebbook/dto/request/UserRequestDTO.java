package ptit.example.btlwebbook.dto.request;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class UserRequestDTO {
    @Email(message = "email invalid format")
    private String email;
    @NotBlank(message = "fullName must be not blank")
    private String fullName;

    private String phoneNumber;

}
