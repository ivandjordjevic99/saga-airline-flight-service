package com.saga.airlinesystem.flightservice.rabbitmq.messages;

import java.time.Instant;
import java.util.UUID;

public abstract class BaseMessage {

    private UUID id;
    private Instant timestamp;
}
