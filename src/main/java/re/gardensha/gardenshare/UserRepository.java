package re.gardensha.gardenshare;

import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface UserRepository extends CrudRepository<User, Integer> {
    public List<User> findUserByOauthId(String oauthId);
}