package com.saga.airlinesystem.flightservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.saga.airlinesystem.flightservice.model.FlightSeatStatus;
import lombok.Data;

@Data
public class FlightSeatDto {

    @JsonProperty("seat_number")
    private String flightSeatNumber;

    @JsonProperty("status")
    private FlightSeatStatus status;
}
