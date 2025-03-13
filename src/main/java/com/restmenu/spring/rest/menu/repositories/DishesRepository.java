package com.restmenu.spring.rest.menu.repositories;

import com.restmenu.spring.rest.menu.models.Dishes;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DishesRepository extends JpaRepository<Dishes, Long> {
    List<Dishes> findByPriceBetween(double minPrice, double maxPrice);
    List<Dishes> findByDiscountTrue();
    @Query("SELECT d FROM Dishes d WHERE d.weight <= :maxWeight ORDER BY d.weight ASC")
    List<Dishes> findDishesUnderWeight(@Param("maxWeight") double maxWeight);
}



