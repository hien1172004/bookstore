package ptit.example.btlwebbook.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ptit.example.btlwebbook.utils.OrderStatus;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TrackingDTO {
    private OrderStatus status;
    private LocalDateTime time;
    private Long userId;
}
