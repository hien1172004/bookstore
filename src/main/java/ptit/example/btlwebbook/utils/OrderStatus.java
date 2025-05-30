package ptit.example.btlwebbook.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
public enum OrderStatus {
    AWAITING_CHECK_PAYMENT(0, "Chờ cửa hàng xác nhận"),
    PAYMENT_ACCEPTED(1, "Đã xác nhận đơn hàng"),
    READY_TO_SHIP(2, "Chuẩn bị giao"),
    TRANSIT(3, "Đang trên đường vận chuyển"),
    PICKUP(4, "Kiện hàng sắp đến"),
    DELIVERED(5, "Giao hàng thành công");

    private final int code;
    private final String text;

    OrderStatus(int code, String text) {
        this.text = text;
        this.code = code;
    }
}
