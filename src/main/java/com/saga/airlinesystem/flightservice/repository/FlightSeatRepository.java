package com.saga.airlinesystem.flightservice.repository;

import com.saga.airlinesystem.flightservice.model.FlightSeat;
import com.saga.airlinesystem.flightservice.model.FlightSeatId;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface FlightSeatRepository extends JpaRepository<FlightSeat, FlightSeatId> {

    List<FlightSeat> findByFlightId(UUID flightId);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("""
        select fs
        from FlightSeat fs
        where fs.id.flightId = :flightId
          and fs.id.flightSeatNumber = :seatNumber
    """)
    Optional<FlightSeat> findSeatForUpdate(
            @Param("flightId") UUID flightId,
            @Param("seatNumber") String seatNumber
    );

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("""
        select fs
        from FlightSeat fs
        where fs.reservationId = :reservationId
    """)
    Optional<FlightSeat> findSeatForRelease(
            @Param("reservationId") UUID reservationId
    );
}
