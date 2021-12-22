package com.vishal.flyme.flightreservatio.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.vishal.flyme.entities.User;
import com.vishal.flyme.repository.UserRepository;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserDetailsServiceImpl.class);
	@Autowired
	UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		LOGGER.info("Inside loadUserByUserName");
		User user = userRepository.findByEmail(email);
		System.out.println(user.toString());
		LOGGER.info("finding the user " + user.toString());
		/*
		 * if(user==null) { LOGGER.info("User not found for this email"); throw new
		 * UsernameNotFoundException("User not found for this email"+ email); }
		 * 
		 */
		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
				user.getRoles());
	}

}
