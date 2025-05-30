package ptit.example.btlwebbook.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
public enum PaymentStatus {
    UNPAID(0, "Chưa thanh toán"),
    PAID(2, "Thanh toán thành công"),
    FAILED(1, "Thanh toán thất bại");

    PaymentStatus(int code, String text) {
        this.code = code;
        this.text = text;
    }

    private final int code;
    private final String text;
}
