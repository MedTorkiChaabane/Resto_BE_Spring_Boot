package com.example.webTeam.validation.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.webTeam.validation.models.Dish;
import com.example.webTeam.validation.models.User;
import com.example.webTeam.validation.services.DishService;
import com.example.webTeam.validation.services.UserService;

@RestController
@RequestMapping("api/dishes")
public class DishController {

	@Autowired
	DishService dishService;
	@Autowired
	UserService userService;

	@GetMapping
	public List<Dish> getAllDishes() {
		return dishService.getAllDishes();
	}
	@PostMapping
	public Dish addDish(@RequestBody Dish dish) {
		Long userId = dish.getUser().getId();
		User user= userService.findUserById(userId);
		dish.setUser(user);
		return dishService.addDish(dish);
	}

	@PutMapping
	public Dish editDish(@RequestBody Dish dish) {
		return dishService.editDish(dish);
	}

	@GetMapping("/{id}")
	public Dish getDishById(@PathVariable Long id) {
		return dishService.getDishById(id);
	}

	@DeleteMapping("/{id}")
	public void deleteDishById(@PathVariable Long id) {
	    dishService.deleteDishById(id);
	}
	
	@GetMapping("chef-dishes/{id}")
	public List<Dish> getDishesByChefId(@PathVariable Long id){
		return dishService.getDishesByChefId(id);
	}
	

}
