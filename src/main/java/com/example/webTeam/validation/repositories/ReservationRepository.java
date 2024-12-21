package com.example.webTeam.validation.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.webTeam.validation.models.Reservation;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation,Long>{
	
	@Query("SELECT r FROM Reservation r WHERE r.user.id = :userId")
	List<Reservation> findAllReservationsClient(@Param("userId") Long userId);
	
	@Query("SELECT r FROM Reservation r WHERE r.dish.user.id = :chefId")
	List<Reservation> findAllReservationsChef(@Param("chefId") Long chefId);
}


