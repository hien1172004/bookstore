package ptit.example.btlwebbook.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ptit.example.btlwebbook.utils.OrderStatus;

import java.time.LocalDateTime;
import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TrackingResponse {
    private OrderStatus status;
    private Date createdAt;
    private long userId;

}
