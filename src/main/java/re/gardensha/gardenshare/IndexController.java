package re.gardensha.gardenshare;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping("/")
    public String root(Model model) {
        model.addAttribute("title", "asdf");
        return "index";
    }

}