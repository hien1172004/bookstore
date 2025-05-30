package ptit.example.btlwebbook.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class VnpayRequest {
    @NotBlank(message = "amount not blank")
    private String amount;
    @NotBlank(message = "paymentId not blank")
    private String paymentId;
}
