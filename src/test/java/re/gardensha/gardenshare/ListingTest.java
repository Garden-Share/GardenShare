package re.gardensha.gardenshare;

import static org.junit.Assert.assertEquals;

import java.sql.Timestamp;

import org.junit.Test;

public class ListingTest{
    // These tests are by John Potz


    @Test
    public void testListingInitialization(){
        String testFruitName = "Apples";
        Listing listing = new Listing(testFruitName, 0.5f, "kg", 5, new Timestamp(System.currentTimeMillis()), new Timestamp(System.currentTimeMillis()), new User("Hi"));
        
        assertEquals(testFruitName, listing.getType());
    }

}