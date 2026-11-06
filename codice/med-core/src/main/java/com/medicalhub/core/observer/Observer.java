package com.medicalhub.core.observer;

/**
 * Interfaccia Observer per la ricezione delle notifiche.
 */
public interface Observer {
    void update(NotificationEvent event);
}
