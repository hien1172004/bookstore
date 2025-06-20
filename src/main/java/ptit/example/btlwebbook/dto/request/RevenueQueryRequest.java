package ptit.example.btlwebbook.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RevenueQueryRequest {
    private LocalDate start;
    private LocalDate end;
}
