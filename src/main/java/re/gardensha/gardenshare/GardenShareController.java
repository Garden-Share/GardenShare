package re.gardensha.gardenshare;
import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;

public class GardenShareController {
    @Autowired
    private UserRepository userRepository;

    @ModelAttribute("user")
    public User getUser(Principal user) 
    {
        if (user == null) {
            return null;
        }
        List<User> potentialUsers = userRepository.findUserByOauthId(user.getName());
        if (potentialUsers.size() == 1) {
            return potentialUsers.get(0);
        }
        return null;
    }
}