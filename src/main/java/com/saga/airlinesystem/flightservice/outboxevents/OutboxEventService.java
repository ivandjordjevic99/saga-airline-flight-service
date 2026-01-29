package com.saga.airlinesystem.flightservice.outboxevents;

import com.saga.airlinesystem.flightservice.rabbitmq.messages.BaseMessage;

public interface OutboxEventService {

    void persistOutboxEvent(String exchange, String routingKey, BaseMessage payload);
}
