package com.example.webTeam.validation.servicesImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.webTeam.validation.models.Reservation;
import com.example.webTeam.validation.repositories.ReservationRepository;
import com.example.webTeam.validation.services.ReservationService;

@Service
public class ReservationServiceImpl implements ReservationService{
	
	@Autowired
	ReservationRepository reservationRepository;
	
	@Override
	public List<Reservation> getAllReservation() {
		
		return reservationRepository.findAll();
	}

	
	@Override
	public Reservation addReservation(Reservation reservation) {
		return reservationRepository.save(reservation);
	}

	@Override
	public void deleteReservation(Long id) {
		 reservationRepository.deleteById(id);
	}

	@Override
	public List<Reservation> findAllReservationsClient(Long id) {
		return reservationRepository.findAllReservationsClient(id);
	}

	@Override
	public List<Reservation> findAllReservationsChef(Long id) {
		return reservationRepository.findAllReservationsChef(id);
	}

	

	

}
