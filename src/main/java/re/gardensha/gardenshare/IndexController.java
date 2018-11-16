package re.gardensha.gardenshare;

import org.springframework.beans.factory.annotation.Autowired;
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