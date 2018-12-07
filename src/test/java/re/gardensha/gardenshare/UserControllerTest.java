package re.gardensha.gardenshare;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class UserControllerTest {
	// These tests are by Wesley Benica
	
	@LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void gettingAnExistingUserShouldWork() {
        User empty = new User("empty");
        userRepository.save(empty);
        ResponseEntity<User> response = this.restTemplate.getForEntity("http://localhost:" + port + "/user/" + empty.getId(), User.class);
        assertThat(response.getStatusCodeValue()).isEqualTo(200); 
        // 200 OK
        assertThat(response.getBody().getId()).isEqualTo(empty.getId());
    }

	@Autowired
	private UserController controller;
	
	@Test
	public void contextLoads() throws Exception {
		assertThat(controller).isNotNull();
	}
}