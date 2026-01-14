package com.saga.airlinesystem.flightservice.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FlightSeatId implements Serializable {

    @Column(name = "flight_id", nullable = false)
    private UUID flightId;

    @Column(name = "flight_seat_number", nullable = false)
    private String flightSeatNumber;

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        FlightSeatId flightSeatId = (FlightSeatId) obj;
        return Objects.equals(flightId, flightSeatId.flightId) &&
                Objects.equals(flightSeatNumber, flightSeatId.flightSeatNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(flightId, flightSeatNumber);
    }
}
