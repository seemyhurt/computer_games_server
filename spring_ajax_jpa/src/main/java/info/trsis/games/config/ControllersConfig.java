package info.trsis.games.config;

import org.springframework.lang.NonNull;
import org.springframework.context.annotation.Configuration;

import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;

@Configuration
public class ControllersConfig implements WebMvcConfigurer 
{
    @Override
    public void addViewControllers(@NonNull ViewControllerRegistry registry) {
        registry.addViewController("/pages/games").setViewName("games");
        registry.addViewController("/pages/developers").setViewName("developers");
        registry.addViewController("/pages/publishers").setViewName("publishers");
    }
}
