package com.medicalhub.core.model.role;

public class DoctorRole implements UserRole {
    
    private String registrationNumber;
    private String specialization;

    public DoctorRole(String registrationNumber, String specialization) {
        this.registrationNumber = registrationNumber;
        this.specialization = specialization;
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
    public String getRoleName() {
        return "DOCTOR";
    }
}