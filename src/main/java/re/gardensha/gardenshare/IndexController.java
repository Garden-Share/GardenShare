package re.gardensha.gardenshare;

import java.security.Principal;
import java.util.Map;
import org.springframework.security.core.Authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @Autowired
    private ListingRepository listingRepository;



    @GetMapping("/")
    public String root(Model model) {
        Iterable<Listing> listings = listingRepository.findAll();
        model.addAttribute("listings", listings);
        return "index";
    }

}