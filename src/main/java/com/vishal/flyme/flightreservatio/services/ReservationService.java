package com.vishal.flyme.flightreservatio.services;

import com.vishal.flyme.entities.Reservation;
import com.vishal.flyme.reservation.dto.ReservatoinReq;

public interface ReservationService {

	public Reservation bookFlight(ReservatoinReq resReq);
}
