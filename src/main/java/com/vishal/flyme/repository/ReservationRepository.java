package com.vishal.flyme.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vishal.flyme.entities.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {


}
