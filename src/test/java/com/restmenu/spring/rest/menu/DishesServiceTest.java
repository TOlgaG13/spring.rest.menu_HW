package com.restmenu.spring.rest.menu;

import static org.junit.jupiter.api.Assertions.*;

import com.restmenu.spring.rest.menu.controllers.MenuController;
import com.restmenu.spring.rest.menu.models.Dishes;
import com.restmenu.spring.rest.menu.repositories.DishesRepository;
import com.restmenu.spring.rest.menu.services.DishesService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import java.util.List;
import java.util.Optional;

@SpringBootTest
class DishesServiceTest {
	@Autowired
	private DishesRepository dishesRepository;

	@Autowired
	private DishesService dishesService;
	@Autowired
	private MenuController menuController;

	private Dishes testDish;

	@BeforeEach
	void setUp() {
		testDish = new Dishes();
		testDish.setName("Pizza");
		dishesRepository.save(testDish);
	}

	@Test
	void testGetDishById() {
		Optional<Dishes> foundDish = dishesRepository.findById(testDish.getId());

		assertTrue(foundDish.isPresent());
		assertEquals("Pizza", foundDish.get().getName());
	}

	@Test
	void testGetAllDishes() {
		ResponseEntity<List<Dishes>> response = menuController.getAllDishes();
		assertNotNull(response);
		assertFalse(response.getBody().isEmpty());
	}

	@Test
	void testGetDishesByPriceRange() {
		testDish.setPrice(10.0);
		dishesRepository.save(testDish);

		ResponseEntity<List<Dishes>> response = menuController.getDishesByPrice(5.0, 15.0);
		assertNotNull(response);
		assertFalse(response.getBody().isEmpty());
		assertEquals(1, response.getBody().size());
		assertEquals("Pizza", response.getBody().get(0).getName());
	}
}
