package com.saga.airlinesystem.flightservice.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "flight_seats")
@Getter
@Setter
public class FlightSeat {

    @EmbeddedId
    private FlightSeatId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("flightId")
    @JoinColumn(name = "flight_id")
    private Flight flight;

    @Column(name = "flight_seat_status", nullable = false)
    @Enumerated(EnumType.STRING)
    private FlightSeatStatus flightSeatStatus;

    @Column(name = "ticket_order_id", unique = true)
    private UUID ticketOrderId;

    public FlightSeat(Flight flight, String flightSeatNumber) {
        this.flight = flight;
        this.flightSeatStatus = FlightSeatStatus.AVAILABLE;
        this.id = new FlightSeatId(flight.getId(), flightSeatNumber);
        this.ticketOrderId = null;
    }

    public FlightSeat() {

    }
}
