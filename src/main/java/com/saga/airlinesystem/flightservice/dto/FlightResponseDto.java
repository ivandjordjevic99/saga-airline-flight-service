package com.saga.airlinesystem.flightservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.saga.airlinesystem.flightservice.model.FlightStatus;
import lombok.Data;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@Data
public class FlightResponseDto {

    @JsonProperty("flight_id")
    private UUID flightId;

    @JsonProperty("flight_number")
    private String flightNumber;

    @JsonProperty("departure_airport")
    private String departureAirport;

    @JsonProperty("arrival_airport")
    private String arrivalAirport;

    @JsonProperty("departure_time")
    private OffsetDateTime departureTime;

    @JsonProperty("arrival_time")
    private OffsetDateTime arrivalTime;

    @JsonProperty("company_code")
    private String companyCode;

    @JsonProperty("flight_status")
    private FlightStatus flightStatus;

    @JsonProperty("flight_distance_miles")
    private Integer flightDistanceMiles;

    @JsonProperty("flight_seats")
    private List<FlightSeatDto> flightSeats;
}
