package com.medicalhub.core.model;

import java.time.LocalDateTime;

/**
 * Rappresenta una singola seduta di riabilitazione programmata o eseguita.
 */
public class Session {
    private String id;
    private String planId;
    private LocalDateTime dateTime;
    private String physioTaxCode;
    private String roomId;
    private double romDegrees;  // Range Of Motion misurato in gradi (es. 0 - 180)
    private int painLevel;      // Livello di dolore percepito da 0 (nessuno) a 10 (atroce)
    private boolean completed;
    private String clinicalNotes;

    public Session(String id, String planId, LocalDateTime dateTime, String physioTaxCode, String roomId) {
        this.id = id;
        this.planId = planId;
        this.dateTime = dateTime;
        this.physioTaxCode = physioTaxCode;
        this.roomId = roomId;
        this.romDegrees = 0.0;
        this.painLevel = 0;
        this.completed = false;
        this.clinicalNotes = "";
    }

    /**
     * Registra l'esito clinico della seduta eseguita.
     */
    public void recordOutcome(double romDegrees, int painLevel, String clinicalNotes) {
        if (painLevel < 0 || painLevel > 10) {
            throw new IllegalArgumentException("Il livello di dolore deve essere compreso tra 0 e 10");
        }
        if (romDegrees < 0.0 || romDegrees > 360.0) {
            throw new IllegalArgumentException("I gradi ROM devono essere compresi tra 0 e 360");
        }
        this.romDegrees = romDegrees;
        this.painLevel = painLevel;
        this.clinicalNotes = clinicalNotes != null ? clinicalNotes : "";
        this.completed = true;
    }

    public String getId() {
        return id;
    }

    public String getPlanId() {
        return planId;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String getPhysioTaxCode() {
        return physioTaxCode;
    }

    public void setPhysioTaxCode(String physioTaxCode) {
        this.physioTaxCode = physioTaxCode;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public double getRomDegrees() {
        return romDegrees;
    }

    public int getPainLevel() {
        return painLevel;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public String getClinicalNotes() {
        return clinicalNotes;
    }

    public void setClinicalNotes(String clinicalNotes) {
        this.clinicalNotes = clinicalNotes;
    }
}
