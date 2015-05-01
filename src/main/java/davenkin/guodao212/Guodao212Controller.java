package davenkin.guodao212;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Davenkin on 9/18/14.
 */

@Controller
@RequestMapping("/")
public class Guodao212Controller {

    @RequestMapping(method = RequestMethod.GET)
    public String home()  {
        return "guodao212";
    }

}
