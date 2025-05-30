package ptit.example.btlwebbook.config;

import com.cloudinary.Cloudinary;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;
@Configuration
public class ConfigCloudinary {
    @Bean
    public Cloudinary configKey(){
        Map<String, String> config = new HashMap<>();
        config.put("cloud_name", "dw9krx7ac");
        config.put("api_key", "343217657647922");
        config.put("api_secret", "PITHfB4ErAvx0gVeU55gQGd3dFc");
        return new Cloudinary(config);
    }
}
