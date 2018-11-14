package re.gardensha.gardenshare;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableAutoConfiguration
@RestController
public class GardenshareApplication {

	public static void main(String[] args) {
		SpringApplication.run(GardenshareApplication.class, args);
	}
	

	
	
	
	
}
