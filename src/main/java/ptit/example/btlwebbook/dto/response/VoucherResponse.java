package ptit.example.btlwebbook.dto.response;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class VoucherResponse {
    private Long id;
    private String name;
    private String code;
    private String createdBy;
    private double value;
    private LocalDate start;
    private LocalDate end;
    private Double minimum;
}
