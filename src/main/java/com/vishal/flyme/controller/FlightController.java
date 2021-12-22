package com.vishal.flyme.controller;

import java.text.ParseException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.vishal.flyme.entities.Flight;
import com.vishal.flyme.repository.FlightRepository;

@Controller
public class FlightController {
	@Autowired
	FlightRepository flightRepo;

	private static final Logger LOGGER=LoggerFactory.getLogger(FlightController.class);
	@RequestMapping("findFlights")
	public String findFlights(@RequestParam("from") String from, @RequestParam("to") String to,
			@RequestParam("departureDate") String departureDate, ModelMap modelMap) throws ParseException {

		LOGGER.info("Inside findFlight()");
		
		java.text.SimpleDateFormat fmt = new java.text.SimpleDateFormat("MM-dd-yyyy");
		java.util.Date ud = fmt.parse(departureDate);
		java.sql.Date sd = new java.sql.Date(ud.getTime());

		System.out.println(sd);
		List<Flight> flights = flightRepo.findFlights(from, to, sd);

		modelMap.addAttribute("flights", flights);
		for (Flight flight : flights)
			System.out.println(flight.toString());
		return "displayFlights";
	}

	@RequestMapping("/display")
	public String dis() {
		LOGGER.info("Inside Display()");
		return "findFlights";
	}
	
	@RequestMapping("admin/showAddFlight")
	public String showAddFlight()
	{
		return "addFlight";
		
	}
	

}
