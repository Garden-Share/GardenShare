package re.gardensha.gardenshare;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ChatController extends GardenShareController {
   
   @RequestMapping("/message")
   public String message() {
      return "message";
   }
}