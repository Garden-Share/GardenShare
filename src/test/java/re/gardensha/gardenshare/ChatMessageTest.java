package re.gardensha.gardenshare;

import org.junit.Test;
import static org.junit.Assert.*;

public class ChatMessageTest{
    // These tests are by John Potz


    @Test
    public void testChatMessageInitialization(){
        String testContent = "Hi! How are you?";
        ChatMessage chatMessage = new ChatMessage(testContent, null, null, null);

        assertEquals(testContent, chatMessage.getContent());
    }

}