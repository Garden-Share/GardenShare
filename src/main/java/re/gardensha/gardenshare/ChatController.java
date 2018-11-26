package re.gardensha.gardenshare;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ChatController {
   
   @RequestMapping("/message")
   public String message() {
      return "message";
   }
}