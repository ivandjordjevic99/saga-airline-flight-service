package com.saga.airlinesystem.flightservice.rabbitmq.messages;

import lombok.Getter;

import java.time.Instant;
import java.util.UUID;

@Getter
public abstract class BaseMessage {

    private final UUID id;
    private final Instant timestamp;

    public BaseMessage() {
        this.id = UUID.randomUUID();
        this.timestamp = Instant.now();
    }

    public BaseMessage(UUID id, Instant timestamp) {
        this.id = id;
        this.timestamp = timestamp;
    }
}
