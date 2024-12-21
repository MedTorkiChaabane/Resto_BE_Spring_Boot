package com.example.webTeam.validation.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.webTeam.validation.models.Dish;

@Repository
public interface DishRepository extends JpaRepository<Dish, Long> {
	
	@Query("SELECT d FROM Dish d WHERE d.user.id = :id")
	List<Dish> getDishesByChefId(@Param("id") long id);
}
