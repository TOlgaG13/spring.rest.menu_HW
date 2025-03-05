package com.restmenu.spring.rest.menu.controllers;

import com.restmenu.spring.rest.menu.models.Dishes;
import com.restmenu.spring.rest.menu.services.DishesService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Controller
@RequestMapping("/dishes")
public class MenuController {
    private final DishesService service;

    public MenuController(DishesService service) {
        this.service = service;
    }
    @GetMapping("/add")
    public String showAddDishPage() {
        return "add_dish";
    }
    @PostMapping("/save")
    public String createDish(@ModelAttribute Dishes dish) {
        service.saveDishes(dish);
        return "redirect:/dishes/list";
    }
    @GetMapping("/list")
    public String showAllDishesPage(Model model) {
        List<Dishes> dishes = service.getAllDishes();
        model.addAttribute("dishes", dishes);
        return "index";
    }
    @GetMapping("/api/all")
    @ResponseBody
    public ResponseEntity<List<Dishes>> getAllDishes() {
        return ResponseEntity.ok(service.getAllDishes());
    }
    @GetMapping("/api/price")
    @ResponseBody
    public ResponseEntity<List<Dishes>> getDishesByPrice(@RequestParam double minPrice, @RequestParam double maxPrice) {
        return ResponseEntity.ok(service.getDishesByPriceRange(minPrice, maxPrice));
    }
    @GetMapping("/api/discount")
    @ResponseBody
    public ResponseEntity<List<Dishes>> getDishesWithDiscount() {
        return ResponseEntity.ok(service.getDishesWithDiscount());
    }
    @GetMapping("/filter")
    public String filterDishes(@RequestParam double minPrice, @RequestParam double maxPrice, Model model) {
        List<Dishes> dishes = service.getDishesByPriceRange(minPrice, maxPrice);
        model.addAttribute("dishes", dishes);
        return "filteredDishes";
    }
    }

