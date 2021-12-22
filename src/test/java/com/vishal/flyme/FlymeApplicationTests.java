package com.vishal.flyme;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.vishal.flyme.controller.UserController;
import com.vishal.flyme.entities.User;
import com.vishal.flyme.repository.UserRepository;
@DisplayName("Testing Flyme Application")
@SpringBootTest
@TestInstance(Lifecycle.PER_METHOD)
class FlymeApplicationTests {
	@InjectMocks
	UserController userController;
	@Mock
	UserRepository userRepository;

	@DisplayName("while testing login method")
	@RepeatedTest(5)
	@Test
	void testLogin() {
		String loginResponse="login";
		User user= new User();
		User savedUser=new User();
		
		when(userRepository.save(user)).thenReturn(savedUser);
		String opUser=userController.register(savedUser);
		assertNotNull(opUser);
		assertNotNull(user);
		assertNotNull(savedUser);
		assertEquals(loginResponse, opUser);
		
		
		
		
	}

}
