package ch.kalajdzic.web.config;

import ch.kalajdzic.web.WelcomeHTMLController;
import ch.kalajdzic.web.service.ApiController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Spring {


    @Bean
    public ApiController apiController() {
        return new ApiController();
    }

    @Bean
    public WelcomeHTMLController welcomeHTMLController() {
        return new WelcomeHTMLController();
    }
}
