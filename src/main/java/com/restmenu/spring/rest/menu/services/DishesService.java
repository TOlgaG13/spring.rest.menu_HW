package com.restmenu.spring.rest.menu.services;
import com.restmenu.spring.rest.menu.models.Dishes;
import com.restmenu.spring.rest.menu.repositories.DishesRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class DishesService {
    private final DishesRepository dishesRepository;

    public DishesService(DishesRepository dishesRepository) {
        this.dishesRepository = dishesRepository;
    }

    @Transactional
    public void addDish(Dishes dish) {
        dishesRepository.save(dish);
    }

    @Transactional(readOnly = true)
    public Page<Dishes> getDishesByPrice(double minPrice, double maxPrice, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return dishesRepository.findByPriceBetween(minPrice, maxPrice, pageable);
    }


    @Transactional(readOnly = true)
    public Page<Dishes> getDishesWithDiscount(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return dishesRepository.findByDiscountTrue(pageable);
    }

    @Transactional(readOnly = true)
    public List<Dishes> getDishesUnderWeight(double maxWeight) {
        return dishesRepository.findDishesUnderWeight(maxWeight);
    }

    @Transactional(readOnly = true)
    public Page<Dishes> getDishes(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return dishesRepository.findAll(pageable);
    }

    }




