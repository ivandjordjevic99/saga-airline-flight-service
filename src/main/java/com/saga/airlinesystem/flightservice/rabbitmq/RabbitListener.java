package com.saga.airlinesystem.flightservice.rabbitmq;

import com.saga.airlinesystem.flightservice.rabbitmq.messages.ReleaseSeatMessage;
import com.saga.airlinesystem.flightservice.rabbitmq.messages.ReserveSeatRequestMessage;
import com.saga.airlinesystem.flightservice.service.FlightSeatService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;
import tools.jackson.databind.ObjectMapper;

import static com.saga.airlinesystem.flightservice.rabbitmq.RabbitMQConstants.RELEASE_SEAT_REQUEST_KEY;
import static com.saga.airlinesystem.flightservice.rabbitmq.RabbitMQConstants.RESERVE_SEAT_REQUEST_KEY;

@Service
@RequiredArgsConstructor
public class RabbitListener {

    private final ObjectMapper objectMapper;
    private final FlightSeatService flightSeatService;

    @org.springframework.amqp.rabbit.annotation.RabbitListener(queues = RabbitMQConstants.FLIGHT_QUEUE)
    public void handle(String payload, @Header("amqp_receivedRoutingKey") String routingKey) {
        System.out.println("Received message " + routingKey);
        switch (routingKey) {
            case RESERVE_SEAT_REQUEST_KEY:
                ReserveSeatRequestMessage reserveSeatRequestMessage = objectMapper.readValue(payload, ReserveSeatRequestMessage.class);
                flightSeatService.reserveFlightSeat(
                        reserveSeatRequestMessage.getReservationId(),
                        reserveSeatRequestMessage.getFlightId(),
                        reserveSeatRequestMessage.getSeatNumber()
                );
                break;
            case RELEASE_SEAT_REQUEST_KEY:
                ReleaseSeatMessage releaseSeatMessage = objectMapper.readValue(payload, ReleaseSeatMessage.class);
                flightSeatService.releaseSeat(releaseSeatMessage.getReservationId());
            default:
                System.out.println("Unknown routing key: " + routingKey);
        }
    }

}
