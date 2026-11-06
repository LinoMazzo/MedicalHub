package com.medicalhub.core.observer;

import java.time.LocalDateTime;

/**
 * Evento inviato agli Observer contenente i dettagli della notifica.
 */
public class NotificationEvent {
    public enum EventType {
        STATE_CHANGE,
        CRITICAL_SESSION_OUTCOME,
        SESSION_BOOKED
    }

    private final EventType type;
    private final String planId;
    private final String message;
    private final LocalDateTime timestamp;

    public NotificationEvent(EventType type, String planId, String message) {
        this.type = type;
        this.planId = planId;
        this.message = message;
        this.timestamp = LocalDateTime.now();
    }

    public EventType getType() {
        return type;
    }

    public String getPlanId() {
        return planId;
    }

    public String getMessage() {
        return message;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        return "[" + type + "] Piano: " + planId + " -> " + message;
    }
}
