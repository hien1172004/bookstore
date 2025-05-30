package ptit.example.btlwebbook.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class VoucherDTO {
    @NotBlank(message = "name must not blank")
    private String name;
    @NotBlank(message = "code must not blank")
    private String code;
    private String createdBy;
    @Positive(message = "value greater 0")
    private double value;

    private LocalDate start;

    private LocalDate end;
    private Double minimum = 0.0;

}
