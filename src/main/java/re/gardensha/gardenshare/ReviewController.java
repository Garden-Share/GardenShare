package re.gardensha.gardenshare;

import java.io.IOException;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReviewController extends GardenShareController {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/user/review/{id}")
    public Review getReview(@PathVariable(name="id") Integer id, HttpServletResponse res) throws IOException{
        Optional<Review> review = reviewRepository.findById(id);
        if (review.isPresent()) {
            return review.get();
        }
        res.sendError(404, "Review not found");
        return null;
    }

    @GetMapping("/user/review/new")
    public Review createReview(Principal user,
                               @RequestParam(value="message") String message,
                               @RequestParam(value="user") Integer userId,
                               @RequestParam(value="rating") Integer rating,
                               HttpServletResponse res) throws InvalidReviewException, IOException {
        List<User> potentialUser = userRepository.findUserByOauthId(user.getName());
        if (potentialUser.size() != 1) {
            res.sendError(500, "Invalid user for reviewer");
            return null;
        }
        User reviewer = potentialUser.get(0);
        Optional<User> potentialReviewee = userRepository.findById(userId);
        if (!potentialReviewee.isPresent()) {
            res.sendError(500, "Invalid user for reviewee");
            return null;
        }
        User reviewee = potentialReviewee.get();
        if (rating > 5 || rating < 1) {
            // If the rating is not in the 1-5 range
            throw new InvalidReviewException("Rating was not between 1 and 5");
        }
        if (reviewer.equals(reviewee)){
            throw new InvalidReviewException("You cannot review yourself!");
        }
        Review newReview = new Review(message, rating, reviewee, reviewer);
        reviewRepository.save(newReview);
        return newReview;
    }

}