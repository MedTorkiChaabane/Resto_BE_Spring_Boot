package com.example.webTeam.validation.servicesImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.webTeam.validation.models.Dish;
import com.example.webTeam.validation.repositories.DishRepository;
import com.example.webTeam.validation.services.DishService;

@Service
public class DishServiceImpl implements DishService {

	@Autowired
	private DishRepository dishRepository;

	@Override
	public List<Dish> getAllDishes() {
		// TODO Auto-generated method stub
		return dishRepository.findAll() ;
	}

	@Override
	public Dish getDishById(Long id){
		// TODO Auto-generated method stub
		Optional<Dish> dish = dishRepository.findById(id);
		return dish.isPresent() ? dish.get() : null;
	}

	@Override
	public void deleteDishById(Long id) {
		// TODO Auto-generated method stub
		dishRepository.deleteById(id);
		
	}

	@Override
	public Dish addDish(Dish dish) {
		// TODO Auto-generated method stub
		return dishRepository.save(dish);
	}

	@Override
	public Dish editDish(Dish dish) {
		// TODO Auto-generated method stub
		return dishRepository.save(dish);
	}

	@Override
	public List<Dish> getDishesByChefId(Long id){
		return dishRepository.getDishesByChefId(id);
	}
}
