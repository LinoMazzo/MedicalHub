package com.medicalhub.core.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * Observer che invia promemoria di seduta o stato al Paziente.
 */
public class PatientReminderNotifier implements Observer {
    private final List<String> reminderLogs = new ArrayList<>();

    @Override
    public void update(NotificationEvent event) {
        String reminder = "[PROMEMORIA PAZIENTE] " + event.getTimestamp() + " - " + event.getMessage();
        reminderLogs.add(reminder);
        System.out.println(reminder);
    }

    public List<String> getReminderLogs() {
        return new ArrayList<>(reminderLogs);
    }
}
