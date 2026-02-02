package com.saga.airlinesystem.flightservice.service.impl;

import com.saga.airlinesystem.flightservice.dto.FlightSeatDto;
import com.saga.airlinesystem.flightservice.model.Flight;
import com.saga.airlinesystem.flightservice.model.FlightSeat;
import com.saga.airlinesystem.flightservice.model.FlightSeatStatus;
import com.saga.airlinesystem.flightservice.outboxevents.OutboxEventService;
import com.saga.airlinesystem.flightservice.rabbitmq.messages.SeatReservationResultMessage;
import com.saga.airlinesystem.flightservice.repository.FlightSeatRepository;
import com.saga.airlinesystem.flightservice.service.FlightSeatService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.saga.airlinesystem.flightservice.rabbitmq.RabbitMQConstants.*;


@Service
@RequiredArgsConstructor
@Slf4j
public class FlightSeatServiceImpl implements FlightSeatService {

    private final FlightSeatRepository flightSeatRepository;
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
    public void reserveFlightSeat(String reservationId, String flightId, String seatNumber) {
        Optional<FlightSeat> optionalSeat = flightSeatRepository.findSeatForUpdate(UUID.fromString(flightId), seatNumber);
        SeatReservationResultMessage message = new SeatReservationResultMessage(reservationId);

        if(optionalSeat.isEmpty()) {
            message.setResolution("Seat not found");
            outboxEventService.persistOutboxEvent(TICKET_RESERVATION_EXCHANGE, SEAT_RESERVATION_FAILED_KEY, message);
            return;
        }
        FlightSeat seat = optionalSeat.get();

        if (seat.getFlightSeatStatus() == FlightSeatStatus.AVAILABLE) {
            System.out.println("Seat " + seatNumber + " is available. Reserving flight seat.");
            seat.setFlightSeatStatus(FlightSeatStatus.RESERVED);
            seat.setReservationId(UUID.fromString(reservationId));
            flightSeatRepository.save(seat);

            message.setMiles(seat.getFlight().getFlightDistanceMiles());
            message.setResolution("Seat reservation successful");
            outboxEventService.persistOutboxEvent(TICKET_RESERVATION_EXCHANGE, SEAT_RESERVED_KEY, message);
            log.info("Seat reservation {}, flight id {}, seat number {} successful", reservationId, flightId, seatNumber);
        } else {
            message.setResolution("Seat already reserved");
            outboxEventService.persistOutboxEvent(TICKET_RESERVATION_EXCHANGE, SEAT_RESERVATION_FAILED_KEY, message);
            log.info("Seat reservation {}, flight id {}, seat number {} failed because seat is already reserved",
                    reservationId, flightId, seatNumber);
        }
    }

    @Override
    @Transactional
    public void releaseSeat(String reservationId) {
        Optional<FlightSeat> optionalSeat = flightSeatRepository.findSeatForRelease(UUID.fromString(reservationId));

        if(optionalSeat.isEmpty()) {
            log.error("Flight seat with reservation id {} not found", reservationId);
            return;
        }
        FlightSeat seat = optionalSeat.get();

        if (seat.getFlightSeatStatus() == FlightSeatStatus.RESERVED) {
            seat.setFlightSeatStatus(FlightSeatStatus.AVAILABLE);
            seat.setReservationId(null);

            log.info("Seat {} of flight {} successfully released from reservation {}",
                    seat.getId().getFlightSeatNumber(), seat.getId().getFlightId(), reservationId);
        } else {
            log.error("Flight seat with reservation id {} was not in state RESERVED", reservationId);
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
