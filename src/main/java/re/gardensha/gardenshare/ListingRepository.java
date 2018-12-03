package re.gardensha.gardenshare;

import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface ListingRepository extends CrudRepository<Listing, Integer> {
    public List<Listing> findListingByCreatedBy(User owner);
}