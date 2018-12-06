package re.gardensha.gardenshare;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface ChatRepository extends CrudRepository<Chat, Integer> {
      @Query(value = "SELECT * FROM Chat c WHERE c.user_one = :userId OR c.user_two = :userId"
                  + " ORDER BY c.last_msg ASC", nativeQuery = true)
      public List<Chat> findChatRoomByOauthId(@Param("userId") String userId);
}