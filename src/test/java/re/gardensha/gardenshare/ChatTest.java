package re.gardensha.gardenshare;

import static org.junit.Assert.assertEquals;

import java.sql.Time;

import org.junit.Test;

public class ChatTest {
	// Test by Michael Curtis

	@Test
	public void testChat() {
		User user1 = new User("oauth1");
		User user2 = new User("oauth2");

		Chat chat = new Chat(user1.getOAuthId(), user2.getOAuthId(), null);

		assertEquals(chat.getSender(), user1.getOAuthId());
		assertEquals(chat.getReceiver(), user2.getOAuthId());

	}

	@Test
	public void testChatMessage() {
		User user1 = new User("oauth1");
		User user2 = new User("oauth2");
		Chat chat = new Chat(user1.getOAuthId(), user2.getOAuthId(), null);

		ChatMessage chatM = new ChatMessage("The message", new Time(0), user1, user2, chat);
		assertEquals(chat, chatM.getChatRoom());
		assertEquals("The message", chatM.getContent());
		assertEquals(user1, chatM.getSentUser());
	}

}
