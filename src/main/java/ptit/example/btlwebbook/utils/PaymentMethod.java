    package ptit.example.btlwebbook.utils;

    import lombok.AllArgsConstructor;
    import lombok.Getter;
    import lombok.NoArgsConstructor;


    @Getter
    public enum PaymentMethod {
        CASH(0, "Cash"),
        MOMO(1, "MoMo"),
        VNPAY(2, "VnPay");

        private final int code;
        private final String text;

        PaymentMethod(int code, String text) {
            this.code = code;
            this.text = text;
        }
    }
