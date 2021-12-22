package com.vishal.flyme.flightreservatio.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class SecurityServiceImpl implements SecurityService {

	private static final Logger LOGGER = LoggerFactory.getLogger(SecurityServiceImpl.class);

	
	@Autowired
	UserDetailsServiceImpl detailsServiceImpl;

	@Autowired
	AuthenticationManager manager;
	@Override
	public boolean login(String email, String password) {
		LOGGER.info("Inside login method "+email+password);
		UserDetails userDetails = detailsServiceImpl.loadUserByUsername(email);

		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userDetails, password,
				userDetails.getAuthorities());

		LOGGER.info("Before authenticating token");
		manager.authenticate(token);
		LOGGER.info("After authenticating token");
		boolean result = token.isAuthenticated();

		if (result) {
			SecurityContextHolder.getContext().setAuthentication(token);
		}

		return result;
	}

}
