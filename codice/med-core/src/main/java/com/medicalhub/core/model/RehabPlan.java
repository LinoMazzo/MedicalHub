package com.medicalhub.core.model;

import com.medicalhub.core.observer.NotificationEvent;
import com.medicalhub.core.observer.Observable;
import com.medicalhub.core.observer.Observer;
import com.medicalhub.core.state.DraftState;
import com.medicalhub.core.state.RehabPlanState;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Entità RehabPlan. Contiene il ciclo di vita (State Pattern) e il meccanismo di notifica (Observer Pattern).
 */
public class RehabPlan implements Observable {
    private String id;
    private String patientTaxCode;
    private String doctorTaxCode;
    private String protocol;          // Descrizione protocollo (es. "Riabilitazione LCA Ginocchio")
    private LocalDate startDate;
    private int plannedSessions;
    private int executedSessions;
    private RehabPlanState state;
    private final List<Session> sessions;
    private final List<Observer> observers;

    public RehabPlan(String id, String patientTaxCode, String doctorTaxCode, String protocol, LocalDate startDate, int plannedSessions) {
        this.id = id;
        this.patientTaxCode = patientTaxCode;
        this.doctorTaxCode = doctorTaxCode;
        this.protocol = protocol;
        this.startDate = startDate;
        this.plannedSessions = plannedSessions;
        this.executedSessions = 0;
        this.state = new DraftState();
        this.sessions = new ArrayList<>();
        this.observers = new ArrayList<>();
    }

    // --- Metodi dello State Pattern ---
    public void prescribe() {
        state.prescribe(this);
    }

    public void startTreatment() {
        state.startTreatment(this);
    }

    public void suspend(String reason) {
        state.suspend(this, reason);
    }

    public void completeTreatment() {
        state.completeTreatment(this);
    }

    public void issueClearance() {
        state.issueClearance(this);
    }

    public void setState(RehabPlanState state) {
        this.state = state;
    }

    public RehabPlanState getState() {
        return state;
    }

    public String getStateName() {
        return state.getStateName();
    }

    // --- Gestione Sedute ---
    public void addSession(Session session) {
        this.sessions.add(session);
        if (session.isCompleted()) {
            this.executedSessions++;
        }
    }

    public void incrementExecutedSessions() {
        this.executedSessions++;
    }

    // --- Metodi dell'Observable Pattern ---
    @Override
    public void attach(Observer observer) {
        if (observer != null && !observers.contains(observer)) {
            observers.add(observer);
        }
    }

    @Override
    public void detach(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(NotificationEvent event) {
        for (Observer observer : observers) {
            observer.update(event);
        }
    }

    public void notifyStateChanged(String info) {
        notifyObservers(new NotificationEvent(
                NotificationEvent.EventType.STATE_CHANGE,
                id,
                "Nuovo stato per il piano " + id + ": [" + getStateName() + "]. " + info
        ));
    }

    // --- Getters e Setters ---
    public String getId() { return id; }
    public String getPatientTaxCode() { return patientTaxCode; }
    public String getDoctorTaxCode() { return doctorTaxCode; }
    public String getProtocol() { return protocol; }
    public LocalDate getStartDate() { return startDate; }
    public int getPlannedSessions() { return plannedSessions; }
    public int getExecutedSessions() {
        long completedInSessions = sessions.stream().filter(Session::isCompleted).count();
        return Math.max(executedSessions, (int) completedInSessions);
    }
    public void setExecutedSessions(int executedSessions) { this.executedSessions = executedSessions; }
    public List<Session> getSessions() { return Collections.unmodifiableList(sessions); }
}
