package ch.kalajdzic.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/welcome")
public class WelcomeHTMLController {

    @RequestMapping("/")
    public ModelAndView helloWorld() {
        return new ModelAndView("welcome");
    }

}