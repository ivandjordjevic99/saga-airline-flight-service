package com.saga.airlinesystem.flightservice.service.impl;

import com.saga.airlinesystem.flightservice.dto.FlightSeatDto;
import com.saga.airlinesystem.flightservice.model.Flight;
import com.saga.airlinesystem.flightservice.model.FlightSeat;
import com.saga.airlinesystem.flightservice.model.FlightSeatStatus;
import com.saga.airlinesystem.flightservice.repository.FlightSeatRepository;
import com.saga.airlinesystem.flightservice.service.FlightSeatService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FlightSeatServiceImpl implements FlightSeatService {

    private final FlightSeatRepository flightSeatRepository;

    @Override
    public void createSeatsForFlight(Flight flight) {
        List<String> seatNumbers = createSeatNumbers();
        List<FlightSeat> flightSeats = new ArrayList<>();

        for (String seatNumber : seatNumbers) {
            FlightSeat flightSeat = new FlightSeat(flight, seatNumber);
            flightSeats.add(flightSeat);
        }

        flightSeatRepository.saveAll(flightSeats);
    }

    @Override
    public List<FlightSeatDto> getSeatsForFlight(UUID flightId) {
        return flightSeatRepository.findByFlightId(flightId).stream().map(this::toDto).toList();
    }

    @Override
    @Transactional
    public void reserveSeatForFlight(String flightId, String seatNumber) {
        FlightSeat seat = flightSeatRepository
                .findSeatForUpdate(UUID.fromString(flightId), seatNumber)
                .orElseThrow(() -> new RuntimeException("Seat not found"));

        if (seat.getFlightSeatStatus() == FlightSeatStatus.AVAILABLE) {

            seat.setFlightSeatStatus(FlightSeatStatus.RESERVED);

            //Save outbox

        } else {

            //Save outbox
        }

    }


    private List<String> createSeatNumbers() {
        List<String> seatNumbers = new ArrayList<>();

        for(int i = 1; i <= 10; i++) {
            for(char j = 'A'; j <= 'F'; j++) {
                seatNumbers.add(j + String.valueOf(i));
            }
        }
        return seatNumbers;
    }

    private FlightSeatDto toDto(FlightSeat flightSeat) {
        FlightSeatDto flightSeatDto = new FlightSeatDto();
        flightSeatDto.setFlightSeatNumber(flightSeat.getId().getFlightSeatNumber());
        flightSeatDto.setStatus(flightSeat.getFlightSeatStatus());
        return flightSeatDto;
    }
}
