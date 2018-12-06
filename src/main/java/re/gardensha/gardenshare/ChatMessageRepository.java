package re.gardensha.gardenshare;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface ChatMessageRepository extends CrudRepository<ChatMessage, Integer> {
   @Query(value = "SELECT * FROM chat_message c WHERE c.fk_chatroomid = :roomid ORDER BY c.send_time ASC", nativeQuery = true)
   public List<ChatMessage> findMessageByRoomId(@Param("roomid") int roomid);
}