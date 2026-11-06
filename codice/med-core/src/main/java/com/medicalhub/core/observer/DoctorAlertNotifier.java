package com.medicalhub.core.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * Observer che notifica il Medico in caso di anomalie cliniche o avanzamento di stato.
 */
public class DoctorAlertNotifier implements Observer {
    private final List<String> alertLogs = new ArrayList<>();

    @Override
    public void update(NotificationEvent event) {
        String alert = "[ALERT MEDICO] " + event.getTimestamp() + " - " + event.getMessage();
        alertLogs.add(alert);
        System.out.println(alert);
    }

    public List<String> getAlertLogs() {
        return new ArrayList<>(alertLogs);
    }
}
