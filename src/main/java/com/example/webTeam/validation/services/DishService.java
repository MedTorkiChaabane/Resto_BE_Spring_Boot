package com.example.webTeam.validation.services;

import java.util.List;

import com.example.webTeam.validation.models.Dish;

public interface DishService {
		
		public List<Dish> getAllDishes();
		
		public Dish getDishById(Long id);
		
		public void deleteDishById(Long id);
		
		public Dish addDish(Dish dish); 
		
		public Dish editDish(Dish dish);
		
		public List<Dish> getDishesByChefId(Long chefId);
	
}
