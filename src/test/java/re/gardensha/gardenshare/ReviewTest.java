package re.gardensha.gardenshare;

import org.junit.Test;
import static org.junit.Assert.*;

public class ReviewTest{
    // These tests are by Alex Gravenor


    @Test
    public void testReviewInitialization(){
        String testString = "AString! AString!";
        User guy = new User("user one");
        User guyTwo = new User("user two");
        Review review = new Review(testString, 3, guy, guyTwo);
        assertEquals(testString, review.getMessage());
    }

}