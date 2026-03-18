package com.saga.airlinesystem.flightservice.rabbitmq;

import com.saga.airlinesystem.flightservice.exceptions.customexceptions.EventAlreadyReceivedException;
import com.saga.airlinesystem.flightservice.inboxevents.InboxEventService;
import com.saga.airlinesystem.flightservice.inboxevents.InboxEventType;
import com.saga.airlinesystem.flightservice.rabbitmq.messages.ReleaseSeatMessage;
import com.saga.airlinesystem.flightservice.rabbitmq.messages.ReserveSeatRequestMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;
import tools.jackson.core.JacksonException;
import tools.jackson.databind.ObjectMapper;


import static com.saga.airlinesystem.flightservice.rabbitmq.RabbitMQConstants.RELEASE_SEAT_REQUEST_KEY;
import static com.saga.airlinesystem.flightservice.rabbitmq.RabbitMQConstants.RESERVE_SEAT_REQUEST_KEY;

@Service
@RequiredArgsConstructor
@Slf4j
public class RabbitListener {

    private final ObjectMapper objectMapper;
    private final InboxEventService inboxEventService;

    @org.springframework.amqp.rabbit.annotation.RabbitListener(queues = RabbitMQConstants.FLIGHT_QUEUE)
    public void handle(String payload, @Header("amqp_receivedRoutingKey") String routingKey) {
        try {
            log.info("Received message {}", routingKey);
            switch (routingKey) {
                case RESERVE_SEAT_REQUEST_KEY:
                    handleReserveSeatEvent(payload);
                    break;
                case RELEASE_SEAT_REQUEST_KEY:
                    handleReleaseSeatEvent(payload);
                    break;
                default:
                    throw new IllegalArgumentException("Unknown routing key" + routingKey);
            }
        } catch (EventAlreadyReceivedException e) {
            log.warn(e.getMessage());
        } catch (JacksonException | IllegalArgumentException e) {
            log.error(e.getMessage());
        }

    }

    private void handleReleaseSeatEvent(String payload) {
        ReleaseSeatMessage releaseSeatMessage = objectMapper.readValue(payload, ReleaseSeatMessage.class);
        inboxEventService.saveInboxEvent(releaseSeatMessage.getId(), payload, InboxEventType.RELEASE_SEAT_REQUEST);
        log.info("Received release seat event for ticket order id: {}", releaseSeatMessage.getTicketOrderId());
    }

    private void handleReserveSeatEvent(String payload) {
        ReserveSeatRequestMessage reserveSeatRequestMessage = objectMapper.readValue(payload, ReserveSeatRequestMessage.class);
        inboxEventService.saveInboxEvent(reserveSeatRequestMessage.getId(), payload, InboxEventType.RESERVE_SEAT_REQUEST);
        log.info("Received reserve seat event for ticket order id: {}", reserveSeatRequestMessage.getTicketOrderId());
    }

}
