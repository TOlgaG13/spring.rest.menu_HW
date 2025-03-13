package com.restmenu.spring.rest.menu.config;

import com.restmenu.spring.rest.menu.models.Dishes;


import com.restmenu.spring.rest.menu.services.DishesService;
import com.restmenu.spring.rest.menu.services.OrderDishesService;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

@Configuration
public class AppConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
                .addResourceHandler("/static/**")
                .addResourceLocations("/WEB-INF/static/");

    }
    @Bean
    public CommandLineRunner demo(final DishesService dishesService, final OrderDishesService orderDishesService) {
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
                Dishes dish;
                for (int i = 1; i <= 5; i++) {
                    dish = new Dishes("Dish " + i, i * 10.0, i * 200, i % 2 == 0);
                    dishesService.addDish(dish);
                }
                Dishes firstDish = new Dishes("Special dish", 50.0, 300, true);
                dishesService.addDish(firstDish);
                orderDishesService.addDishToOrder(firstDish);
            }
        };
    }
    @Bean
    public ApplicationRunner listEndpoints(ApplicationContext context) {
        return args -> {
            System.out.println(">>> Доступні контролери та їх маршрути:");
            RequestMappingHandlerMapping mapping = context.getBean(RequestMappingHandlerMapping.class);
            mapping.getHandlerMethods().forEach((key, value) -> System.out.println(key + " => " + value));
        };
    }
}

