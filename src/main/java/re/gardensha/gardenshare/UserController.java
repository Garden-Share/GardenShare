package re.gardensha.gardenshare;

import java.io.IOException;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepo;

    @RequestMapping(value = "/user")
    public ModelAndView user(Principal principal, HttpServletResponse res) throws IOException {
        ModelAndView result = new ModelAndView("user");
        List<User> possibleMatching = userRepo.findUserByOauthId(principal.getName());
        if (possibleMatching.isEmpty()) {
            User newUser = new User(principal.getName());
            userRepo.save(newUser);
            result.addObject("user", newUser);
            return result;
        } else {
            if (possibleMatching.size() != 1) {
                res.sendError(500, "There exists two users with the same oauth id! Please contact an admin!");
                return null;
            }
            result.addObject("user", possibleMatching.get(0));
            return result;
        }
    }

    @RequestMapping(value = "/user/{id}")
    public ModelAndView getUser(@PathVariable("id") int userId, HttpServletResponse res) throws IOException {
        ModelAndView result = new ModelAndView("user");
        Optional<User> possibleUser = userRepo.findById(userId);
        if (!possibleUser.isPresent()) {
            res.sendError(404, "No user with that id was found");
            return null;
        }
        User user = possibleUser.get();
        result.addObject("user", user);
        return result;
    }

    @RequestMapping(value = "/user/edit")
    public ModelAndView editUser(Principal principal, @RequestParam(value="name") Optional<String> name, HttpServletResponse res) throws IOException {
        ModelAndView result = new ModelAndView("user");

        // Find the user based on who is logged in
        List<User> possibleMatching = userRepo.findUserByOauthId(principal.getName());
        if (possibleMatching.size() != 1){
            res.sendError(500, "");
            return null;
        }
        User user = possibleMatching.get(0);
        
        // Update with all present fields
        if (name.isPresent()){
            user.setName(name.get());
        }
        userRepo.save(user);

        result.addObject("user", user);
        return result;
    }

}
