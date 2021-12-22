package com.vishal.flyme.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vishal.flyme.entities.Passenger;

public interface PassengerRepository extends JpaRepository<Passenger, Long> {

}
