package com.saga.airlinesystem.flightservice.inboxevents;

import java.util.UUID;

public interface InboxEventService {

    void saveInboxEvent(UUID messageId, String payload, InboxEventType inboxEventType);
}
