package re.gardensha.gardenshare;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ListingController {

    @Autowired
    private ListingRepository listingRepository;


	@GetMapping(path="/listing")
	public Listing getListing(@RequestParam(value="id") int id, HttpServletResponse res) throws IOException {
        Optional<Listing> listing = listingRepository.findById(id);
        if (listing.isPresent()) {
            return listing.get();
        }
        res.sendError(404, "Listing with id "+id+" not found");
        return null;
    }

    
    @PostMapping(path="/listing/new")
    public Listing createListing(@RequestParam(value="type") String type){
        Listing newListing = new Listing(type);
        listingRepository.save(newListing);
        return newListing;
    }
	
}
