package re.gardensha.gardenshare;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {

	@RequestMapping("/health")
	public String checkHealth() {
		return "Ok";
	}
	
}
