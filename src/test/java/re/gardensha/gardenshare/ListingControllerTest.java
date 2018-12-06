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
public class ListingControllerTest {
    // These tests are by Alex Gravenor
	
	@LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private ListingRepository listingRepository;

    @Test
    public void postingAListingWithNoParametersShouldNotAddListing() throws Exception {
        long listingCount = listingRepository.count();
        this.restTemplate.postForObject("http://localhost:" + port + "/listing/new", null, String.class);
        assertThat(listingCount).isEqualTo(listingRepository.count());
    }

	@Autowired
	private ListingController controller;
	
	@Test
	public void contextLoads() throws Exception {
		assertThat(controller).isNotNull();
	}
	
}
