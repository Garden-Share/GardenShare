package re.gardensha.gardenshare;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface ReviewRepository extends CrudRepository<Review, Integer> {
    public List<Review> findReviewByReviewer(User reviewer);
    public List<Review> findReviewByReviewee(User reviewee);
}