package ptit.example.btlwebbook.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

@Embeddable
@Data
public class CostInfo {
    @Column(name = "sub_total")
    private Double subTotal; // Tổng phụ

    @Column(name = "shipping_fee", columnDefinition = "DOUBLE default 0")
    private Double shippingFee; // Phí vận chuyển, mặc định 0

    private Double discount; // Giảm giá

    private Double total; // Tổng cộng
}
