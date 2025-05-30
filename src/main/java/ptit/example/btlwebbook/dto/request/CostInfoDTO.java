package ptit.example.btlwebbook.dto.request;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CostInfoDTO {

    private Double subTotal; // Tổng phụ


    private Double shippingFee; // Phí vận chuyển, mặc định 0

    private Double discount; // Giảm giá

    private Double total; // Tổng cộng
}
