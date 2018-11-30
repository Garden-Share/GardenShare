package re.gardensha.gardenshare;

import org.junit.Test;
import static org.junit.Assert.*;

public class UserTest{
    // These tests are by Alex Gravenor


    @Test
    public void testUserInitialization(){
        String testString = "AString! AString!";
        User guy = new User(testString);
        assertEquals(testString, guy.getOAuthId());
    }

}