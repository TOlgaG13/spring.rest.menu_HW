package com.restmenu.spring.rest.menu.controllers;

import com.restmenu.spring.rest.menu.models.Dishes;
import com.restmenu.spring.rest.menu.models.OrderDishes;
import com.restmenu.spring.rest.menu.services.DishesService;
import com.restmenu.spring.rest.menu.services.OrderDishesService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller

public class MenuController {
    static final int ITEMS_PER_PAGE = 6;
    private final DishesService dishesService;
    private final OrderDishesService orderDishesService;

    public MenuController(DishesService dishesService, OrderDishesService orderDishesService) {
        this.dishesService = dishesService;
        this.orderDishesService = orderDishesService;
    }
    @GetMapping("/")
    public String index(Model model, @RequestParam(defaultValue = "0") int page) {
        int pageSize = ITEMS_PER_PAGE;
        Page<Dishes> dishesPage = dishesService.getDishes(page, pageSize);
        model.addAttribute("dishesPage", dishesPage);
        return "index";
    }

    @GetMapping("/dish/add")
    public String showAddDishPage(Model model) {
        model.addAttribute("dish", new Dishes());
        return "add-dish";
    }
    @PostMapping("/dish/add")
    public String addDish(@ModelAttribute Dishes dish) {
        dishesService.addDish(dish);
        return "redirect:/";
    }
    @GetMapping("/dish/filter-price")
    public String filterByPrice(@RequestParam double minPrice,
                                @RequestParam double maxPrice,
                                @RequestParam(defaultValue = "0") int page,
                                @RequestParam(defaultValue = "5") int size,
                                Model model) {
        Page<Dishes> dishesPage = dishesService.getDishesByPrice(minPrice, maxPrice, page, size);
        model.addAttribute("dishesPage", dishesPage);
        return "index";
    }

    @GetMapping("/dishes/under-1kg")
    public String getDishesUnder1Kg(Model model) {
        List<Dishes> dishes = dishesService.getDishesUnderWeight(1000);
        model.addAttribute("dishes", dishes);
        model.addAttribute("dishesPage", null);
        return "index";
    }

    @GetMapping("/dish/discount")
    public String filterByDiscount(Model model, @RequestParam(defaultValue = "0") int page) {
        int pageSize = ITEMS_PER_PAGE;
        Page<Dishes> dishesPage = dishesService.getDishesWithDiscount(page, pageSize);
        model.addAttribute("dishesPage", dishesPage);
        return "index";
    }


    @PostMapping("/order/add/{id}")
    public String addDishToOrder(@PathVariable Long id) {
        Dishes dish = dishesService.getDishById(id);

        if (dish != null) {
            orderDishesService.addDishToOrder(dish);
        }
        return "redirect:/";
    }

    @PostMapping("/order/remove/{id}")
    public String removeDishFromOrder(@PathVariable Long id) {
        Dishes dish = dishesService.getDishById(id);

        if (dish != null) {
            orderDishesService.removeDishFromOrder(dish);
        }
        return "redirect:/orders";
    }
    @GetMapping("/orders")
    public String showOrders(Model model) {
        List<OrderDishes> orders = orderDishesService.getAllOrders();
        model.addAttribute("orders", orders);
        return "orders";
    }
    @GetMapping("/reset")
    public String resetDishes() {
        dishesService.reset();
        return "redirect:/";
    }

    }

