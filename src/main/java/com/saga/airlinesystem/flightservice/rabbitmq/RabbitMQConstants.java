package com.saga.airlinesystem.flightservice.rabbitmq;

public class RabbitMQConstants {

    // queue
    public static final String FLIGHT_QUEUE = "flight.events";

    // exchanges
    public static final String TICKET_BOOKING_EXCHANGE = "ticket-booking.exchange";

    // routing keys
    public static final String FLIGHT_REQUESTS_TOPIC = "request.flight.#";
    public static final String RESERVE_SEAT_REQUEST_KEY = "request.flight.seat.reserve";
    public static final String RELEASE_SEAT_REQUEST_KEY = "request.flight.seat.release";

    public static final String SEAT_RESERVED_KEY = "flight.seat.reserved";
    public static final String SEAT_RESERVATION_FAILED_KEY = "flight.seat.reservation_failed";
}
