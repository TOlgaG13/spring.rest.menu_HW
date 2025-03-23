package com.restmenu.spring.rest.menu.config;

import com.restmenu.spring.rest.menu.models.Dishes;
import com.restmenu.spring.rest.menu.models.UserRegisterType;
import com.restmenu.spring.rest.menu.models.UserRole;
import com.restmenu.spring.rest.menu.services.DishesService;
import com.restmenu.spring.rest.menu.services.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;


@Configuration
@EnableWebMvc
@EnableMethodSecurity(prePostEnabled = true)

public class AppConfig implements WebMvcConfigurer {
    public static final String ADMIN_LOGIN = "admin";

    @Bean
    public CommandLineRunner demo(final UserService userService,
                                  final PasswordEncoder encoder,
                                  final DishesService dishesService) {
        return args -> {
            for (int i = 1; i <= 5; i++) {
                Dishes dishes = new Dishes("Dish Name " + i, i * 10.0, i, i % 2 == 1);
                dishesService.addDish(dishes);
            }

            if (userService.findByEmail(ADMIN_LOGIN).isEmpty()) {
                userService.addUser(
                        encoder.encode("password"),
                        UserRole.ADMIN, UserRegisterType.FORM, ADMIN_LOGIN, "", "");
            }

            if (userService.findByEmail("user").isEmpty()) {
                userService.addUser(
                        encoder.encode("password"),
                        UserRole.USER, UserRegisterType.FORM, "user", "", "");
            }
        };
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
                .addResourceHandler("/static/**")
                .addResourceLocations("/WEB-INF/static/");
    }

    @Bean
    public InternalResourceViewResolver internalResourceViewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/pages/");
        resolver.setSuffix(".jsp");
        return resolver;
    }
}