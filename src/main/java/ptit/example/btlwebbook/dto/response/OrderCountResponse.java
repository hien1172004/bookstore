package ptit.example.btlwebbook.dto.response;


import lombok.Getter;
import lombok.Setter;



@Getter
@Setter
public class OrderCountResponse {
    private String date;
    private Long quantity;

    public OrderCountResponse(String date, Long quantity) {
        this.date = date;
        this.quantity = quantity;
    }


}
