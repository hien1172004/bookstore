package ptit.example.btlwebbook.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

//@Getter
//@Setter
//@AllArgsConstructor
//@NoArgsConstructor
public interface RevenueResponse {
    String getDate();
    Double getRevenue();
}
