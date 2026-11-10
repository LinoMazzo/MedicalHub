package com.medicalhub.core.model.role;

public class PatientRole implements UserRole {
    
    private String practicedSport;
    private String injuryHistory;

    public PatientRole(String practicedSport, String injuryHistory) {
        this.practicedSport = practicedSport;
        this.injuryHistory = injuryHistory;
    }

    public String getPracticedSport() {
        return practicedSport;
    }

    public void setPracticedSport(String practicedSport) {
        this.practicedSport = practicedSport;
    }

    public String getInjuryHistory() {
        return injuryHistory;
    }

    public void setInjuryHistory(String injuryHistory) {
        this.injuryHistory = injuryHistory;
    }

    @Override
    public String getRoleName() {
        return "PATIENT";
    }
}