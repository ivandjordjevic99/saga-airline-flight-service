package com.saga.airlinesystem.flightservice.rabbitmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.saga.airlinesystem.flightservice.rabbitmq.RabbitMQConstants.*;

@Configuration
public class RabbitConfiguration {

    @Bean
    public TopicExchange ticketBookingExchange() {
        return new TopicExchange(TICKET_BOOKING_EXCHANGE);
    }

    @Bean
    public Queue flightQueue() {
        return new Queue(FLIGHT_QUEUE, true);
    }

    @Bean
    public Binding flightRequestsBinding(Queue flightQueue, TopicExchange ticketBookingExchange) {
        return BindingBuilder
                .bind(flightQueue)
                .to(ticketBookingExchange)
                .with(FLIGHT_REQUESTS_TOPIC);
    }
}
