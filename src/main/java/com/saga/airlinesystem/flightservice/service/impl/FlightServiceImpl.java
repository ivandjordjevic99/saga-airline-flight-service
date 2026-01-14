package com.saga.airlinesystem.flightservice.service.impl;

import com.saga.airlinesystem.flightservice.dto.FlightRequestDto;
import com.saga.airlinesystem.flightservice.dto.FlightResponseDto;
import com.saga.airlinesystem.flightservice.dto.FlightSeatDto;
import com.saga.airlinesystem.flightservice.exceptions.customexceptions.ResourceNotFoundException;
import com.saga.airlinesystem.flightservice.model.Flight;
import com.saga.airlinesystem.flightservice.model.FlightStatus;
import com.saga.airlinesystem.flightservice.repository.FlightRepository;
import com.saga.airlinesystem.flightservice.service.FlightSeatService;
import com.saga.airlinesystem.flightservice.service.FlightService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FlightServiceImpl implements FlightService {

    private final FlightRepository flightRepository;
    private final FlightSeatService flightSeatService;

    @Override
    public FlightResponseDto createFlight(FlightRequestDto flightRequestDto) {
        Flight flight = new Flight();
        flight.setFlightNumber(flightRequestDto.getFlightNumber());
        flight.setDepartureAirport(flightRequestDto.getDepartureAirport());
        flight.setArrivalAirport(flightRequestDto.getArrivalAirport());
        flight.setDepartureTime(flightRequestDto.getDepartureTime());
        flight.setArrivalTime(flightRequestDto.getArrivalTime());
        flight.setCompanyCode(flightRequestDto.getCompanyCode());
        flight.setFlightStatus(FlightStatus.OPEN_FOR_RESERVATIONS);
        flight.setPrice(flightRequestDto.getPrice());
        flight.setFlightDistanceMiles(flightRequestDto.getFlightDistanceMiles());

        flightRepository.save(flight);
        flightSeatService.createSeatsForFlight(flight);

        return toDto(flight);
    }

    @Override
    public FlightResponseDto cancelFlight(UUID flightId) {
        Flight flight = flightRepository.findById(flightId)
                .orElseThrow(() -> new ResourceNotFoundException("Flight not found"));

        flight.setFlightStatus(FlightStatus.CANCELLED);
        flightRepository.save(flight);
        return toDto(flight);
    }

    @Override
    public List<FlightResponseDto> getAllAvailableFlights() {
        return flightRepository.findByFlightStatus(FlightStatus.OPEN_FOR_RESERVATIONS).stream().map(this::toDto).toList();
    }

    @Override
    public FlightResponseDto getFlightById(UUID flightId) {
        List<FlightSeatDto> flightSeatDtos = flightSeatService.getSeatsForFlight(flightId);
        Flight flight = flightRepository.findById(flightId).orElseThrow(() -> new ResourceNotFoundException("Flight not found"));
        FlightResponseDto flightResponseDto = toDto(flight);
        flightResponseDto.setFlightSeats(flightSeatDtos);
        return flightResponseDto;
    }

    private FlightResponseDto toDto(Flight flight) {
        FlightResponseDto flightResponseDto = new FlightResponseDto();

        flightResponseDto.setFlightNumber(flight.getFlightNumber());
        flightResponseDto.setFlightStatus(flight.getFlightStatus());
        flightResponseDto.setDepartureAirport(flight.getDepartureAirport());
        flightResponseDto.setDepartureTime(flight.getDepartureTime());
        flightResponseDto.setArrivalAirport(flight.getArrivalAirport());
        flightResponseDto.setArrivalTime(flight.getArrivalTime());
        flightResponseDto.setCompanyCode(flight.getCompanyCode());
        flightResponseDto.setFlightId(flight.getId());
        flightResponseDto.setFlightDistanceMiles(flight.getFlightDistanceMiles());
        flightResponseDto.setPrice(flight.getPrice());

        return flightResponseDto;
    }
}
