package com.saga.airlinesystem.flightservice.outboxevents;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class OutboxEventRequestDto {

    @JsonProperty("payload")
    private String payload;

}
