package com.vishal.flyme.flightreservatio.services;

import java.io.FileNotFoundException;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itextpdf.text.DocumentException;
import com.vishal.flyme.entities.Flight;
import com.vishal.flyme.entities.Passenger;
import com.vishal.flyme.entities.Reservation;
import com.vishal.flyme.repository.FlightRepository;
import com.vishal.flyme.repository.PassengerRepository;
import com.vishal.flyme.repository.ReservationRepository;
import com.vishal.flyme.reservation.dto.ReservatoinReq;
import com.vishal.flyme.util.EmailUtil;
import com.vishal.flyme.util.PdfGenerator;

@Service
@Transactional
public class ReservationServiceImpl implements ReservationService {

	@Autowired
	FlightRepository flightRepo;
	@Autowired
	PassengerRepository passRepo;
	@Autowired
	ReservationRepository resRepo;

	@Autowired
	PdfGenerator pdfGenerator;

	@Autowired
	EmailUtil emailutil;

	@Override
	public Reservation bookFlight(ReservatoinReq resReq) {
		// resReq.getCardnumber();
		// makePayment
		String flightNumber = resReq.getFlightNumber();
		List<Flight> fl = flightRepo.findByFlightNumber(flightNumber);
		Flight flight = fl.get(0);
		Passenger passenger = new Passenger();

		passenger.setFirstName(resReq.getfName());
		passenger.setLastName(resReq.getlName());
		passenger.setEmail(resReq.getEmail());
		passenger.setPhone(resReq.getPhone());

		Passenger savedPassenger = passRepo.save(passenger);

		Reservation res = new Reservation();
		res.setFlight(flight);

		res.setPassenger(savedPassenger);
		res.setCheckedIn(false);
		Reservation savedReservation = resRepo.save(res);

		try {
			
			
			String filePath = "D:/FlymeReservations" + savedReservation.getId() + ".pdf";
			
			pdfGenerator.generateItenarary(savedReservation, filePath);
			
			emailutil.sendEmail(passenger.getEmail(), filePath);
			
		} catch (FileNotFoundException | DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return savedReservation;

	}

}
