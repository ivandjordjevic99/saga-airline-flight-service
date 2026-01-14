package com.saga.airlinesystem.flightservice.controller;

import com.saga.airlinesystem.flightservice.dto.FlightRequestDto;
import com.saga.airlinesystem.flightservice.dto.FlightResponseDto;
import com.saga.airlinesystem.flightservice.service.FlightService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/flights")
@RequiredArgsConstructor
public class FlightController {

    private final FlightService flightService;

    @PostMapping
    public ResponseEntity<FlightResponseDto> createFlight(@RequestBody FlightRequestDto flightRequestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(flightService.createFlight(flightRequestDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<FlightResponseDto> deleteFlight(@PathVariable UUID id) {
        return ResponseEntity.ok(flightService.cancelFlight(id));
    }

    @GetMapping
    public ResponseEntity<List<FlightResponseDto>> getAllAvailableFlights() {
        return ResponseEntity.ok(flightService.getAllAvailableFlights());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FlightResponseDto> getFlightById(@PathVariable UUID id) {
        return ResponseEntity.ok(flightService.getFlightById(id));
    }
}
