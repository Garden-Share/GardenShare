package re.gardensha.gardenshare;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class ChatUsersTest {

    @Test
    public void testChatSender(){
    	String testName = "sender";
        User sender = new User("sender");
        User receiver = new User("receiver");
        ChatMessage chatMessage = new ChatMessage(null, null, sender, receiver, null);
        assertEquals(testName, chatMessage.getSentUser().getOAuthId());
    }

    @Test
    public void testChatReceiver(){
    	String testName = "receiver";
        User sender = new User("sender");
        User receiver = new User("receiver");
        ChatMessage chatMessage = new ChatMessage(null, null, sender, receiver, null);
        assertEquals(testName, chatMessage.getReceiveUser().getOAuthId());
    }
}
