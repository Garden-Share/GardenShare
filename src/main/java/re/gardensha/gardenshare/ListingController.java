package re.gardensha.gardenshare;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ListingController {

    @Autowired
    private ListingRepository listingRepository;

    private static String listingObjectName = "listing";

	@GetMapping(path="/listing")
	public ModelAndView getListing(@RequestParam(value="id") int id, HttpServletResponse res) throws IOException {
        ModelAndView result = new ModelAndView("listing");
        Optional<Listing> listing = listingRepository.findById(id);
        if (listing.isPresent()) {
            result.addObject(listingObjectName, listing.get());
            return result;
        }
        res.sendError(404, "Listing with id "+id+" not found");
        return null;
    }

    
    @PostMapping(path="/listing/new")
    public ModelAndView createListing(@RequestParam(value="type") String type){
        ModelAndView result = new ModelAndView("listing/new");
        Listing newListing = new Listing(type);
        listingRepository.save(newListing);
        result.addObject(listingObjectName, newListing);
        return result;
    }
	
}
