package com.medicalhub.core.model.role;

public class PhysiotherapistRole implements UserRole {
    
    private String qualification; // Es. "Terapia Manuale", "Tecar"

    public PhysiotherapistRole(String qualification) {
        this.qualification = qualification;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    @Override
    public String getRoleName() {
        return "PHYSIOTHERAPIST";
    }
}