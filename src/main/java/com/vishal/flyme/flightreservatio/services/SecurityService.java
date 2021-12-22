package com.vishal.flyme.flightreservatio.services;

import org.springframework.stereotype.Service;

@Service
public interface SecurityService {
	boolean login(String username, String password); 
}
