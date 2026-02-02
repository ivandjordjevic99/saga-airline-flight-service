package com.saga.airlinesystem.flightservice.service;

import com.saga.airlinesystem.flightservice.dto.FlightSeatDto;
import com.saga.airlinesystem.flightservice.model.Flight;

import java.util.List;
import java.util.UUID;

public interface FlightSeatService {

    void createSeatsForFlight(Flight flight);
    List<FlightSeatDto> getSeatsForFlight(UUID flightId);
    void reserveFlightSeat(String reservationId, String flightId, String seatNumber);
    void releaseSeat(String reservationId);
}
