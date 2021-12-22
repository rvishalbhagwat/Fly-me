package com.vishal.flyme.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.vishal.flyme.entities.User;
import com.vishal.flyme.flightreservatio.services.SecurityService;
import com.vishal.flyme.repository.UserRepository;

@Controller
public class UserController {
	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

	@Autowired
	SecurityService securityService;
	/*
	 * @Autowired private BCryptPasswordEncoder encoder;
	 */
	@Autowired
	UserRepository userRepo;

	@RequestMapping("/showreg")
	public String showRegPage() {
		LOGGER.info("Inside showregpage()");
		return "registerUser";
	}

	@RequestMapping("/home")
	public String home() {
		LOGGER.info("Inside home()");
		return "home";
	}

	@RequestMapping("/loginpage")
	public String lgp() {
		LOGGER.info("Inside lpg()");
		return "login";
	}

	@RequestMapping(value = "/registerUser", method = RequestMethod.POST)
	public String register(@ModelAttribute("user") User user) {
		LOGGER.info("Inside register()");
		//user.setPassword(encoder.encode(user.getPassword()));
		
		User loginUser = userRepo.save(user);
		System.out.println(user.getFirstName());
		return "login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(@RequestParam("email") String email, @RequestParam("password") String password, ModelMap map) {
		/*
		 * boolean loginResponse = securityService.login(email, password);
		 * LOGGER.info("Inside login() and the email is: " + email); if (loginResponse)
		 * { return "findFlights"; } else { map.addAttribute("msg",
		 * "Invalid user name or password .Please try again."); }
		 */
		
		User user = userRepo.findByEmail(email);
		if(user.getPassword().equals(password)) {
			return "findFlights";
		}
		return "login";
	}
}
