package com.saga.airlinesystem.flightservice.service.impl;

import com.saga.airlinesystem.flightservice.dto.FlightSeatDto;
import com.saga.airlinesystem.flightservice.exceptions.customexceptions.ResourceNotFoundException;
import com.saga.airlinesystem.flightservice.model.Flight;
import com.saga.airlinesystem.flightservice.model.FlightSeat;
import com.saga.airlinesystem.flightservice.model.FlightSeatStatus;
import com.saga.airlinesystem.flightservice.outboxevents.OutboxEventService;
import com.saga.airlinesystem.flightservice.rabbitmq.messages.SeatReservationResultMessage;
import com.saga.airlinesystem.flightservice.repository.FlightRepository;
import com.saga.airlinesystem.flightservice.repository.FlightSeatRepository;
import com.saga.airlinesystem.flightservice.service.FlightSeatService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.saga.airlinesystem.flightservice.rabbitmq.RabbitMQConstants.*;


@Service
@RequiredArgsConstructor
public class FlightSeatServiceImpl implements FlightSeatService {

    private final FlightSeatRepository flightSeatRepository;
    private final FlightRepository flightRepository;
    private final OutboxEventService outboxEventService;

    @Override
    public void createSeatsForFlight(Flight flight) {
        List<String> seatNumbers = createSeatNumbers();
        List<FlightSeat> flightSeats = new ArrayList<>();

        for (String seatNumber : seatNumbers) {
            FlightSeat flightSeat = new FlightSeat(flight, seatNumber);
            flightSeats.add(flightSeat);
        }

        flightSeatRepository.saveAll(flightSeats);
        System.out.println("Created flight seats for flight: " + flight.getId());
    }

    @Override
    public List<FlightSeatDto> getSeatsForFlight(UUID flightId) {
        return flightSeatRepository.findByFlightId(flightId).stream().map(this::toDto).toList();
    }

    @Override
    @Transactional
    public void reserveFlightSeat(String reservationId, String flightId, String email, String seatNumber) {
        Optional<FlightSeat> optionalSeat = flightSeatRepository.findSeatForUpdate(UUID.fromString(flightId), seatNumber);
        SeatReservationResultMessage message = new SeatReservationResultMessage(reservationId);

        if(optionalSeat.isEmpty()) {
            message.setResolution("Seat not found");
            outboxEventService.persistOutboxEvent(TICKET_RESERVATION_EXCHANGE, SEAT_RESERVATION_FAILED_KEY, message);
        }
        FlightSeat seat = optionalSeat.get();

        if (seat.getFlightSeatStatus() == FlightSeatStatus.AVAILABLE) {
            System.out.println("Seat " + seatNumber + " is available. Reserving flight seat.");
            seat.setFlightSeatStatus(FlightSeatStatus.RESERVED);
            seat.setEmail(email);

            message.setResolution("Seat reservation successful");

            Flight flight = flightRepository.findById(UUID.fromString(flightId)).orElseThrow(
                    () -> new ResourceNotFoundException("Flight not found")
            );
            message.setMiles(flight.getFlightDistanceMiles());

            outboxEventService.persistOutboxEvent(TICKET_RESERVATION_EXCHANGE, SEAT_RESERVED_KEY, message);
            System.out.println("Seat reservation successful");
        } else {
            message.setResolution("Seat already reserved");
            outboxEventService.persistOutboxEvent(TICKET_RESERVATION_EXCHANGE, SEAT_RESERVATION_FAILED_KEY, message);
        }
    }


    private List<String> createSeatNumbers() {
        List<String> seatNumbers = new ArrayList<>();

        for(int i = 1; i <= 10; i++) {
            for(char j = 'A'; j <= 'F'; j++) {
                seatNumbers.add(String.valueOf(i) + j);
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
