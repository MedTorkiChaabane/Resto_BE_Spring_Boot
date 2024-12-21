package com.example.webTeam.validation.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.webTeam.validation.models.Reservation;
import com.example.webTeam.validation.services.ReservationService;

@RestController
@RequestMapping("api/reservations")
public class ReservationController {
	@Autowired
	ReservationService reservationService;

	@GetMapping
	List<Reservation> getAllReservation(){
		return reservationService.getAllReservation();
	}
	
	@PostMapping
	Reservation addReservation(@RequestBody Reservation reservation) {
		return reservationService.addReservation(reservation);
	}

	@DeleteMapping("/{id}")
	void deleteReservationById(@PathVariable Long id) {
		reservationService.deleteReservation(id);
	}

	@GetMapping("/client-reservations/{id}")
	List<Reservation> findAllReservationsClient(@PathVariable Long id) {
		return reservationService.findAllReservationsClient(id);
	}

	@GetMapping("/chef-reservations/{id}")
	List<Reservation> findAllReservationsChef(@PathVariable Long id) {
		return reservationService.findAllReservationsChef(id);
	}

}
