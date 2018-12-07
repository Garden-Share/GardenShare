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

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class ChatControllerTest {
   // These tests are by Gilbert Han

   @LocalServerPort
   private int port;

   @Autowired
   private TestRestTemplate restTemplate;

   @Autowired
   private ChatRepository chatRepo;

   @Test
   // post with wrong parameters should not pass
   public void postChat() throws Exception {
      long listingCount = chatRepo.count();
      this.restTemplate.postForObject("http://localhost:" + port + "/chat/compose/6", String.class, String.class);
      assertThat(listingCount).isEqualTo(chatRepo.count());
   }

   @Autowired
   private ChatController controller;

   @Test
   public void contextLoads() throws Exception {
      assertThat(controller).isNotNull();
   }

}
