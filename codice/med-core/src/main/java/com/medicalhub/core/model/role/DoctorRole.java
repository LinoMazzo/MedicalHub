package com.medicalhub.core.model.role;

/**
 * Ruolo concreto per i medici prescrittori.
 */
public class DoctorRole implements UserRole {
    private String registrationNumber; // Numero albo professionale
    private String specialization;     // Specializzazione (es. Fisiatra, Ortopedico)

    public DoctorRole(String registrationNumber, String specialization) {
        this.registrationNumber = registrationNumber;
        this.specialization = specialization;
    }

    @Override
    public String getRoleName() {
        return "Medico (" + specialization + ")";
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    @Override
    public String toString() {
        return "DoctorRole{albo='" + registrationNumber + "', spec='" + specialization + "'}";
    }
}
