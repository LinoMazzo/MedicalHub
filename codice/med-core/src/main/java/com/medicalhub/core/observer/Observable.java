package com.medicalhub.core.observer;

/**
 * Interfaccia Subject / Observable.
 */
public interface Observable {
    void attach(Observer observer);
    void detach(Observer observer);
    void notifyObservers(NotificationEvent event);
}
