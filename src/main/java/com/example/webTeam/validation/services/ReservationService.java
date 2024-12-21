package com.example.webTeam.validation.services;

import java.util.List;

import com.example.webTeam.validation.models.Reservation;

public interface ReservationService {
	public List<Reservation> getAllReservation();
	public Reservation addReservation(Reservation reservation);
	public void deleteReservation(Long id);
	public List<Reservation> findAllReservationsClient(Long id);
	public List<Reservation> findAllReservationsChef(Long id);

}
