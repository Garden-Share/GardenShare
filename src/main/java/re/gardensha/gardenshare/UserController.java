package re.gardensha.gardenshare;

import java.io.IOException;
import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController extends GardenShareController {

    // Regex pattern to match valid email addresses
    // Taken from https://www.regular-expressions.info/email.html
    private static Pattern emailRegex = Pattern.compile("\\A[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\z");
    
    private static String userPageObjectName = "userPage";
    private static String userPageListingName = "listings";
    private static String userPageReviewName = "reviews";

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private ListingRepository listingRepo;

    @Autowired
    private ReviewRepository reviewRepo;

    @RequestMapping(value = "/user")
    public ModelAndView user(Principal principal, HttpServletResponse res) throws IOException {
        ModelAndView result = new ModelAndView("user/show");
        List<User> possibleMatching = userRepo.findUserByOauthId(principal.getName());
        if (possibleMatching.isEmpty()) {
            User newUser = new User(principal.getName());
            userRepo.save(newUser);
            result.addObject(userPageObjectName, newUser);

            List<Listing> listings = listingRepo.findListingByCreatedBy(newUser);
            result.addObject(userPageListingName, listings);
            result.addObject(userPageReviewName, getReviews(newUser));
            return result;
        } else {
            if (possibleMatching.size() != 1) {
                res.sendError(500, "There exists two users with the same oauth id! Please contact an admin!");
                return null;
            }
            User user = possibleMatching.get(0);
            List<Listing> listings = listingRepo.findListingByCreatedBy(user);
            result.addObject(userPageListingName, listings);
            result.addObject(userPageObjectName, user);
            result.addObject(userPageReviewName, getReviews(user));
            return result;
        }
    }

    @RequestMapping(value = "/user/{id}")
    public ModelAndView getUser(@PathVariable("id") int userId, HttpServletResponse res) throws IOException {
        ModelAndView result = new ModelAndView("user/show");
        Optional<User> possibleUser = userRepo.findById(userId);
        if (!possibleUser.isPresent()) {
            res.sendError(404, "No user with that id was found");
            return null;
        }
        User user = possibleUser.get();

        List<Listing> listings = listingRepo.findListingByCreatedBy(user);

        result.addObject(userPageListingName, listings);
        result.addObject(userPageObjectName, user);
        result.addObject(userPageReviewName, getReviews(user));
        return result;
    }

    @RequestMapping(value = "/user/edit")
    public ModelAndView editUser(Principal principal,
                                 @RequestParam(value="name") Optional<String> name,
                                 @RequestParam(value="email") Optional<String> email,
                                 @RequestParam(value="description") Optional<String> description,
                                 HttpServletResponse res) throws IOException {
        ModelAndView result = new ModelAndView("user/edit");

        // Find the user based on who is logged in
        List<User> possibleMatching = userRepo.findUserByOauthId(principal.getName());
        if (possibleMatching.size() != 1){
            res.sendError(500, "");
            return null;
        }
        User user = possibleMatching.get(0);
        
        // Update with all present fields
        if (name.isPresent()){
            user.setName(name.get());
        }
        if (email.isPresent()){
            String emailString = email.get();
            if (emailRegex.matcher(emailString).matches()){
                user.setEmail(email.get());
            }
        }

        if (description.isPresent()) {
            user.setDescription(description.get());
        }

        userRepo.save(user);

        result.addObject(userPageObjectName, user);
        return result;
    }

    private List<Review> getReviews(User user){
        return reviewRepo.findReviewByReviewee(user);
    }

}
