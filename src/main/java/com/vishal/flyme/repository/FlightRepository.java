package com.vishal.flyme.repository;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.vishal.flyme.controller.FlightController;
import com.vishal.flyme.entities.Flight;

public interface FlightRepository extends JpaRepository<Flight, Long> {
	
	@Query("from Flight where departureCity=:departureCity and arrivalCity=:arrivalCity and dateOfDeparture=:dateOfDeparture")
	List<Flight> findFlights(@Param("departureCity") String from, @Param("arrivalCity") String to,
			@Param("dateOfDeparture") Date departureDate);
	
	//@Query("from flight  where Flight_Number=:flightNumber limit 1")
	Flight getById(Long flightId);
	
	@Query("from Flight  where Flight_Number=:flightNumber")
	List<Flight> findByFlightNumber(@Param("flightNumber") String flightNumber);

	
	
	
}
