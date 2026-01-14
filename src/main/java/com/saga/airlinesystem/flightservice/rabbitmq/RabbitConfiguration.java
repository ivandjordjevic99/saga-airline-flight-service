package com.saga.airlinesystem.flightservice.rabbitmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfiguration {

    public static final String FLIGHT_QUEUE = "flight.events";
    public static final String TICKET_RESERVATION_EXCHANGE = "ticket-reservation.exchange";

    @Bean
    public TopicExchange ticketReservationExchange() {
        return new TopicExchange(TICKET_RESERVATION_EXCHANGE);
    }

    @Bean
    public Queue flightQueue() {
        return new Queue(FLIGHT_QUEUE, true);
    }

    @Bean
    public Binding reservationCreatedBinding(Queue flightQueue, TopicExchange ticketReservationExchange) {
        return BindingBuilder
                .bind(flightQueue)
                .to(ticketReservationExchange)
                .with("reservation.created");
    }

    @Bean
    public Binding reservationSuccessfulBinding(Queue flightQueue, TopicExchange ticketReservationExchange) {
        return BindingBuilder
                .bind(flightQueue)
                .to(ticketReservationExchange)
                .with("reservation.successful");
    }
}
