package com.vishal.flyme.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	public void configure(HttpSecurity http) {
		try {
			http.authorizeRequests()
					.antMatchers("/displayFlights", "/display", "/redirect", "/completeBooking", "/completeReservation",
							"/reserve", "/findFlights", "/showreg", "/reservations/*", "/flyme", "/login", "/index",
							"/home", "/registerUser", "/loginpage", "/showLogin", "/login/*")
					.permitAll().antMatchers("admin/*").hasAnyAuthority("ADMIN").anyRequest().authenticated().and()
					.csrf().disable();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Bean(name=BeanIds.AUTHENTICATION_MANAGER)
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();

	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/resources/**", "/templates/*", "/static/**", "/css/**", "/js/**", "/img/**",
				"/vendor/**", "/fonts/**");
	}
}
