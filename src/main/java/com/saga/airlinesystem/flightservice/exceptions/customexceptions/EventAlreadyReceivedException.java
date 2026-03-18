package com.saga.airlinesystem.flightservice.exceptions.customexceptions;

import lombok.Getter;

@Getter
public class EventAlreadyReceivedException extends RuntimeException {

    private final String message;

    public EventAlreadyReceivedException(String message) {
        this.message = message;
    }

}
