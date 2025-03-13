package com.restmenu.spring.rest.menu.services;
import com.restmenu.spring.rest.menu.models.Dishes;
import com.restmenu.spring.rest.menu.repositories.DishesRepository;
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
    public List<Dishes> getDishesByPrice(double minPrice, double maxPrice) {
        return dishesRepository.findByPriceBetween(minPrice, maxPrice);
    }
    @Transactional(readOnly = true)
    public List<Dishes> getDishesWithDiscount() {
        return dishesRepository.findByDiscountTrue();
    }
    @Transactional(readOnly = true)
    public List<Dishes> getDishesUnderWeight(double maxWeight) {
        return dishesRepository.findDishesUnderWeight(maxWeight);
    }
    @Transactional
    public void reset() {
        dishesRepository.deleteAll();
        for (int i = 1; i <= 5; i++) {
            Dishes dish = new Dishes("Dish Name " + i, i * 10.0, i * 100, i % 2 == 0);
            this.addDish(dish);
        }

        for (int i = 6; i <= 10; i++) {
            Dishes dish = new Dishes("Dish Name " + i, i * 12.0, i * 80, false);
            this.addDish(dish);
        }
    }
}



