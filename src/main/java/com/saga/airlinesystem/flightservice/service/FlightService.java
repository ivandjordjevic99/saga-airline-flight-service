package com.saga.airlinesystem.flightservice.service;

import com.saga.airlinesystem.flightservice.dto.FlightRequestDto;
import com.saga.airlinesystem.flightservice.dto.FlightResponseDto;

import java.util.List;
import java.util.UUID;

public interface FlightService {

    FlightResponseDto createFlight(FlightRequestDto flightRequestDto);
    FlightResponseDto cancelFlight(UUID flightId);
    List<FlightResponseDto> getAllAvailableFlights();
    FlightResponseDto getFlightById(UUID flightId);
}
