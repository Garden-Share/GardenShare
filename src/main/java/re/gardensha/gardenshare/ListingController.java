package re.gardensha.gardenshare;

import java.io.IOException;
import java.security.Principal;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ListingController extends GardenShareController {

    @Autowired
    private ListingRepository listingRepository;

    @Autowired
    private UserRepository userRepository;

    private static String listingObjectName = "listing";
    // Use ISO 8601 date format
    private DateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm'Z'");
    private DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    private static Logger logger = Logger.getGlobal();

    @GetMapping(path = "/listing/{id}")
    public ModelAndView getListing(@PathVariable(value = "id") int id, HttpServletResponse res) throws IOException {
        ModelAndView result = new ModelAndView("listing/show");
        Optional<Listing> listing = listingRepository.findById(id);
        if (listing.isPresent()) {
            result.addObject(listingObjectName, listing.get());
            return result;
        }
        res.sendError(404, "Listing with id " + id + " not found");
        return null;
    }

    @GetMapping(path = "/listing/new")
    public ModelAndView createListing() {
        return new ModelAndView("listing/new_entry");
    }

    @PostMapping(path = "/listing/new")
    public ModelAndView createListing(Principal userPrincipal, @RequestParam(value = "type") String type,
            @RequestParam(value = "weight") float weight, @RequestParam(value = "weightUnit") String weightUnit,
            @RequestParam(value = "postalCode") Optional<String> postalCode,
            @RequestParam(value = "count") Optional<Integer> count,
            @RequestParam(value = "start") Optional<String> startTimeStamp,
            @RequestParam(value = "end") Optional<String> endTimeStamp, HttpServletResponse res)
            throws InvalidListingException, IOException {
        ModelAndView result = new ModelAndView("listing/new");
        Timestamp start;
        Timestamp end;
        try {
            if (startTimeStamp.isPresent()) {
                start = parseTime(startTimeStamp.get());
            } else {
                start = new Timestamp(System.currentTimeMillis());
            }
            if (endTimeStamp.isPresent()) {
                end = parseTime(endTimeStamp.get());
            } else {
                end = new Timestamp(System.currentTimeMillis());
            }
        } catch (ParseException exception) {
            logger.info("Start param was " + startTimeStamp);
            logger.info("End param was " + endTimeStamp);
            throw new InvalidListingException("Start or end date had an invalid date format");
        }

        if (userPrincipal == null) {
            res.sendRedirect("/");
            return null;
        }

        List<User> possibleUser = userRepository.findUserByOauthId(userPrincipal.getName());
        if (possibleUser.size() != 1) {
            throw new InvalidListingException("Could not find creator as valid user in database");
        }

        Listing newListing = new Listing(type, weight, weightUnit, count.orElse(-1), start, end, possibleUser.get(0));
        newListing.setPostalCode(postalCode.orElse(""));
        listingRepository.save(newListing);
        result.addObject(listingObjectName, newListing);
        res.sendRedirect("/listing/" + newListing.id);
        return result;
    }

    @GetMapping("/listing/{id}/delete")
    public void deleteListing(Principal userPrincipal, @PathVariable(value = "id") int id, HttpServletResponse res)
            throws IOException {
        List<User> possibleUser = userRepository.findUserByOauthId(userPrincipal.getName());
        if (possibleUser.size() != 1) {
            res.sendError(500, "No user found associated with the request");
        }
        Optional<Listing> listing = listingRepository.findById(id);
        User user = possibleUser.get(0);
        if (listing.isPresent() && listing.get().getUser().getId() == user.getId()) {
            listingRepository.delete(listing.get());
        }
        res.sendRedirect("/");
    }

    private Timestamp parseTime(String timeStamp) throws ParseException {
        try {
            // Try parsing with ISO format first
            return new Timestamp(format.parse(timeStamp).getTime());
        } catch (ParseException e) {
            // If it doesn't work, parse with just the date
            return new Timestamp(dateFormat.parse(timeStamp).getTime());
        }
    }

}
