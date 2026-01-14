package com.saga.airlinesystem.flightservice.repository;

import com.saga.airlinesystem.flightservice.model.Flight;
import com.saga.airlinesystem.flightservice.model.FlightStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface FlightRepository extends JpaRepository<Flight, UUID> {

    List<Flight> findByFlightStatus(FlightStatus status);

}
