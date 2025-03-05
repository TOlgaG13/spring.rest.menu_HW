package com.restmenu.spring.rest.menu.repositories;

import com.restmenu.spring.rest.menu.models.Dishes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface DishesRepository extends JpaRepository<Dishes, Long> {
    List<Dishes> findByPriceBetween(double minPrice, double maxPrice);
    List<Dishes> findByDiscountTrue();
}

