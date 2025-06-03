package ptit.example.btlwebbook;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class BtlwebbookApplication {

	public static void main(String[] args) {
		SpringApplication.run(BtlwebbookApplication.class, args);
	}

}
