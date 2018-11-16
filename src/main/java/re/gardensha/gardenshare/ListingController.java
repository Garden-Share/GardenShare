package re.gardensha.gardenshare;

import java.io.IOException;
import java.sql.Time;
import java.text.DateFormat;
import java.util.Optional;
import java.text.ParseException;

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
    public ModelAndView createListing(@RequestParam(value="type") String type,
                                      @RequestParam(value="weight") float weight,
                                      @RequestParam(value="weightUnit") String weightUnit,
                                      @RequestParam(value="count") Optional<Integer> count,
                                      @RequestParam(value="start") Optional<String> startTimeStamp,
                                      @RequestParam(value="end") String endTimeStamp) throws InvalidListingException {
        ModelAndView result = new ModelAndView("listing/new");
        Time start, end;
        try {
            if (startTimeStamp.isPresent()){
                long startMillis = DateFormat.getInstance().parse(startTimeStamp.get()).getTime();
                start = new Time(startMillis);
            }else{
                start = new Time(System.currentTimeMillis());
            }
            long endMillis = DateFormat.getInstance().parse(endTimeStamp).getTime();
            end = new Time(endMillis);
        }catch(ParseException exception){
            throw new InvalidListingException("Start or end date had an invalid date format");
        }
        Listing newListing = new Listing(type, weight, weightUnit, count.orElse(-1), start, end);
        listingRepository.save(newListing);
        result.addObject(listingObjectName, newListing);
        return result;
    }

	
}
