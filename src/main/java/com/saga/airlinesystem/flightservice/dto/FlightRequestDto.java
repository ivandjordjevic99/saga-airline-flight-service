package com.saga.airlinesystem.flightservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.OffsetDateTime;

@Data
public class FlightRequestDto {

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

    @JsonProperty("flight_distance_miles")
    private Integer flightDistanceMiles;
}
