package com.vishal.flyme.controller;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vishal.flyme.controller.dto.ReservationUpdateRequest;
import com.vishal.flyme.entities.Reservation;
import com.vishal.flyme.repository.ReservationRepository;

@RestController
@CrossOrigin
public class ReservationRestController {
	private static final Logger LOGGER=LoggerFactory.getLogger(ReservationRestController.class);

	@Autowired
	ReservationRepository reserRepository;

	@RequestMapping("/reservations/{id}")
	public Reservation findReservation(@PathVariable("id") Long id) {
		LOGGER.info("Inside findreservation()");
		Optional<Reservation> findByid = reserRepository.findById(id);
		Reservation reservation = findByid.get();
		return reservation;

	}

	@RequestMapping("/reservations")
	public Reservation updateReservation(@RequestBody ReservationUpdateRequest request) {
		LOGGER.info("Inside updateReservation()");
		Optional<Reservation> findByid = reserRepository.findById(request.getId());
		Reservation reservation = findByid.get();
		reservation.setNumberOfBags(request.getNumberOfBags());
		reservation.setCheckedIn(request.getCheckedIn());
		Reservation updatedReservation = reserRepository.save(reservation);
		return updatedReservation;

	}

}
