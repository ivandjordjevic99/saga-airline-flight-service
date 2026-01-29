package com.saga.airlinesystem.flightservice.rabbitmq;

import com.saga.airlinesystem.flightservice.rabbitmq.messages.ReserveSeatCommand;
import com.saga.airlinesystem.flightservice.service.FlightSeatService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;
import tools.jackson.databind.ObjectMapper;

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
                ReserveSeatCommand message = objectMapper.readValue(payload, ReserveSeatCommand.class);
                flightSeatService.reserveFlightSeat(
                        message.getReservationId(),
                        message.getFlightId(),
                        message.getEmail(),
                        message.getSeatNumber()
                );
                break;
            default:
                System.out.println("Unknown routing key: " + routingKey);
        }
    }

}
