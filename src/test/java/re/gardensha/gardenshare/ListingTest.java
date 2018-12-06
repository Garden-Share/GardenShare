package re.gardensha.gardenshare;

import org.junit.Test;
import static org.junit.Assert.*;

public class ListingTest{
    // These tests are by John Potz


    @Test
    public void testListingInitialization(){
        String testFruitName = "Apples";
        Listing listing = new Listing(testFruitName, 0.5f, "kg", 5, new Timestamp(), new Timestamp(), new User("Hi"));
        
        assertEquals(testFruitString, listing.getType());
    }

}