package sia.tacocloud;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ScrController {

    @GetMapping("/scr")
    public String scr() {
        return "scr";
    }

}
