package re.gardensha.gardenshare;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class GardenShareErrorController implements ErrorController {

    @RequestMapping("/error")
    public ModelAndView handleError(HttpServletRequest request) {
        ModelAndView response = new ModelAndView("error");
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        
        if (status != null) {
            Integer statusCode = Integer.valueOf(status.toString());
            response.addObject("code", statusCode);
        }
        return response;
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}