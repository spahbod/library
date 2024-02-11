package my.home.library.config;

import my.home.library.utility.Constraint;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/index").setViewName(Constraint.INDEX);
        registry.addViewController("/").setViewName(Constraint.INDEX);
        registry.addViewController("/login").setViewName("login");
    }
}
