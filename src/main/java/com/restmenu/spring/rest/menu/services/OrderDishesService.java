package com.restmenu.spring.rest.menu.services;

import com.restmenu.spring.rest.menu.models.Dishes;
import com.restmenu.spring.rest.menu.models.OrderDishes;
import com.restmenu.spring.rest.menu.repositories.DishesOrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Service
public class OrderDishesService {
    private final DishesOrderRepository dishesOrderRepository;

    public OrderDishesService(DishesOrderRepository dishesOrderRepository) {
        this.dishesOrderRepository = dishesOrderRepository;
    }
    @Transactional
    public void addDishToOrder(Dishes dish) {
        OrderDishes orderDishes = new OrderDishes(dish);
        dishesOrderRepository.save(orderDishes);
    }
    @Transactional
    public void removeDishFromOrder(Dishes dish) {
        dishesOrderRepository.deleteByDishes(dish);
    }
    @Transactional(readOnly = true)
    public List<OrderDishes> getAllOrders() {
        return dishesOrderRepository.findAll();
    }
}

