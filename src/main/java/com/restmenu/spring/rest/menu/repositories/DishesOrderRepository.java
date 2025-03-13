package com.restmenu.spring.rest.menu.repositories;
import com.restmenu.spring.rest.menu.models.Dishes;
import com.restmenu.spring.rest.menu.models.OrderDishes;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;




@Repository
public interface DishesOrderRepository extends JpaRepository<OrderDishes, Long> {
    void deleteByDishes(Dishes dishes);

}


