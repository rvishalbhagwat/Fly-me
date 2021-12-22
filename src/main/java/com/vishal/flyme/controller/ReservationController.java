package com.vishal.flyme.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.vishal.flyme.entities.Flight;
import com.vishal.flyme.entities.Reservation;
import com.vishal.flyme.flightreservatio.services.ReservationService;
import com.vishal.flyme.repository.FlightRepository;
import com.vishal.flyme.reservation.dto.ReservatoinReq;

@Controller
public class ReservationController {

	private static final Logger LOGGER=LoggerFactory.getLogger(ReservationController.class);
	@Autowired
	FlightRepository flightRepos;
	@Autowired
	ReservationService resService;
	@Autowired
	ReservatoinReq resReq;
	/*
	 * 
	 * @RequestMapping("/completeReservation") public String
	 * showCompleteReservation(@RequestParam("flightNumber") String flightNumber,
	 * ModelMap map) { List<Flight> findById =
	 * flightRepos.findByFlightNumber(flightNumber); Flight flight=findById.get(0);
	 * String str=flight.getFlightNumber(); map.addAttribute("flight", flight);
	 * return "";
	 * 
	 * }
	 */

	@RequestMapping("/redirect")
	public String redirect(@RequestParam(name = "flightNumber") String flightNumber, ModelMap map) {
		LOGGER.info("Inside redirect()");
		System.out.println(flightNumber);
		List<Flight> byFlightNumber = flightRepos.findByFlightNumber(flightNumber);

		if (byFlightNumber.size() != 0) {
			Flight findOne = byFlightNumber.get(0);
			String str = findOne.getFlightNumber();
			map.addAttribute("flight", findOne);
			System.out.println(str + "flightNo");
		}

		return "completeBooking";
	}

	@RequestMapping("/completeReservation")

	public String reserv(@RequestParam(name = "flightNumber") String flightNumber,
			@RequestParam(name = "fName") String fName,

			@RequestParam(name = "lName") String lName, @RequestParam(name = "email") String email,

			@RequestParam(name = "phone") String phone, @RequestParam(name = "cardname") String cardname,

			@RequestParam(name = "cardnumber") String cardnumber, @RequestParam(name = "expDate") String expDate,

			@RequestParam(name = "cvv") String cvv, ModelMap map) {
		

		LOGGER.info("Inside redirect()");
		resReq.setfName(fName);
		resReq.setlName(lName);
		resReq.setEmail(email);
		resReq.setCardname(cardname);
		resReq.setCardnumber(cardnumber);
		resReq.setCvv(cvv);
		resReq.setExpDate(expDate);
		resReq.setFlightNumber(flightNumber);
		resReq.setPhone(phone);
		System.out.println("inside post reservation " );

		Reservation reservation = resService.bookFlight(resReq);
		map.addAttribute("msg", "Reservation Created sucessfully" );

		return "reserve";
	}

}
