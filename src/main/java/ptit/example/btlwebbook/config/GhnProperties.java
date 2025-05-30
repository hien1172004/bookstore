package ptit.example.btlwebbook.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "ghn")
@Getter
@Setter
public class GhnProperties {
    private String token;
    private String shopId;
    private Integer fromDistrictId;
    private String fromWardCode;
}
