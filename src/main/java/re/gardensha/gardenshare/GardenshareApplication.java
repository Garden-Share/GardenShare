package re.gardensha.gardenshare;

import java.security.Principal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableAutoConfiguration
@RestController
public class GardenshareApplication {

	public static void main(String[] args) {
		SpringApplication.run(GardenshareApplication.class, args);
	}
	
	@RequestMapping(value = "/user")
   public Principal user(Principal principal) {
      return principal;
   }
	
	
	
	
}
