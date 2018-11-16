package re.gardensha.gardenshare;

import org.junit.Test;
import static org.junit.Assert.*;

public class UserTest{


    @Test
    public void testUserInitialization(){
        String testString = "AString! AString!";
        User guy = new User(testString);
        assertEquals(testString, guy.getOAuthId());
    }

}