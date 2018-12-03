package re.gardensha.gardenshare;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)

@Suite.SuiteClasses({
    GardenshareApplicationTests.class,
    HealthTest.class,
    IndexTest.class,
    ListingControllerTest.class,
    ReviewControllerTest.class
})
public class AlexIntegrationTestSuite {
    // These tests are by Alex Gravenor
}