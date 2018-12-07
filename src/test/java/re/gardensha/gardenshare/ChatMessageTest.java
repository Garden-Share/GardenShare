package re.gardensha.gardenshare;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.Date;

public class ChatMessageTest {
    // These tests are by John Potz

    @Test
    public void testChatMessageInitialization() {
        String testContent = "Hi! How are you?";
        ChatMessage chatMessage = new ChatMessage(testContent, null, null, null, null);

        assertEquals(testContent, chatMessage.getContent());
    }

    // Two unit test by Gilbert Han
    @Test
    public void testMessage() {
        String testContent = "content";
        Chat room = new Chat();
        User sentUser = new User("userone");
        User usertwo = new User("usertwo");

        ChatMessage msg = new ChatMessage(testContent, new Date(), sentUser, usertwo, room);

        // checks if correct user is set as sent user
        assertEquals(sentUser.getOAuthId(), msg.getSentUser().getOAuthId());

        // check if correct room is set for each message
        assertEquals(room.getId(), msg.getChatRoom().getId());

    }

}