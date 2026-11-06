package com.medicalhub.core.model.role;

/**
 * Ruolo concreto per i pazienti della struttura riabilitativa.
 */
public class PatientRole implements UserRole {
    private String sport;
    private String injuryHistory;

    public PatientRole(String sport, String injuryHistory) {
        this.sport = sport != null ? sport : "Nessuno";
        this.injuryHistory = injuryHistory != null ? injuryHistory : "Nessuna anamnesi remota";
    }

    @Override
    public String getRoleName() {
        return "Paziente";
    }

    public String getSport() {
        return sport;
    }

    public void setSport(String sport) {
        this.sport = sport;
    }

    public String getInjuryHistory() {
        return injuryHistory;
    }

    public void setInjuryHistory(String injuryHistory) {
        this.injuryHistory = injuryHistory;
    }

    @Override
    public String toString() {
        return "PatientRole{sport='" + sport + "', injuryHistory='" + injuryHistory + "'}";
    }
}
