package re.gardensha.gardenshare;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class ChatControllerTest {

   // A test by Michael Curtis

   @LocalServerPort
   private int port;

   @Autowired
   private TestRestTemplate template;

   @Autowired
   private ChatRepository chatRepo;

   @Test
   public void testChat1() throws Exception {
      long chatCount = chatRepo.count();
      template.postForObject("http://localhost:" + port + "/chat/compose/-1", null, String.class);
      assertThat(chatCount).isEqualTo(chatRepo.count());
   }

   // These are the test from Gilbert Han
   @Test
   public void testChatPost() throws Exception {
      long count = chatRepo.count();
      chatRepo.save(new Chat());

      // see if it correctly saves the chat room
      assertThat(count + 1).isEqualTo(chatRepo.count());
      count = chatRepo.count();

      MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
      map.add("contet", "hey there");
      template.postForObject("http://localhost:" + port + "/chat/compose/6", map, String.class);
      assertThat(count).isEqualTo(chatRepo.count());
   }

   @Autowired
   private ChatController controller;

   @Test
   public void contextLoads() throws Exception {
      assertThat(controller).isNotNull();
   }

}
