package com.restmenu.spring.rest.menu.services;

import com.restmenu.spring.rest.menu.models.Dishes;
import com.restmenu.spring.rest.menu.repositories.DishesRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class DishesService {
    private final DishesRepository repository;

    public DishesService(DishesRepository repository) {
        this.repository = repository;
    }

    public Dishes saveDishes(Dishes dish) {
        return repository.save(dish);
    }

    public List<Dishes> getAllDishes() {
        return repository.findAll();
    }

    public List<Dishes> getDishesByPriceRange(double minPrice, double maxPrice) {
        return repository.findByPriceBetween(minPrice, maxPrice);
    }

    public List<Dishes> getDishesWithDiscount() {
        return repository.findByDiscountTrue();
    }
}