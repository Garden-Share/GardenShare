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
public class ReviewControllerTest {
    // These tests are by Alex Gravenor
	
	@LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private ReviewRepository reviewRepository;

    @Test
    public void postingAReviewWithNoParametersShouldNotAddReview() throws Exception {
        long reviewCount = reviewRepository.count();
        this.restTemplate.postForObject("http://localhost:" + port + "/user/review/new", null, String.class);
        assertThat(reviewCount).isEqualTo(reviewRepository.count());
    }

    @Test
    public void gettingAnExistingReviewShouldWork() {
        Review empty = new Review("Test Message 123", 3, null, null);
        reviewRepository.save(empty);
        ResponseEntity<Review> response = this.restTemplate.getForEntity("http://localhost:" + port + "/user/review/" + empty.id, Review.class);
        assertThat(response.getStatusCodeValue()).isEqualTo(200); 
        // 200 OK
        assertThat(response.getBody().id).isEqualTo(empty.id);
    }

	@Autowired
	private ReviewController controller;
	
	@Test
	public void contextLoads() throws Exception {
		assertThat(controller).isNotNull();
	}
	
}
