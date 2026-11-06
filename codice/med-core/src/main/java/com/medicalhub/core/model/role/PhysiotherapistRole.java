package com.medicalhub.core.model.role;

/**
 * Ruolo concreto per i fisioterapisti esecutori delle sedute.
 */
public class PhysiotherapistRole implements UserRole {
    private String qualification; // Qualifica (es. Terapia Manuale, Kinesiologia)

    public PhysiotherapistRole(String qualification) {
        this.qualification = qualification;
    }

    @Override
    public String getRoleName() {
        return "Fisioterapista (" + qualification + ")";
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    @Override
    public String toString() {
        return "PhysiotherapistRole{qualifica='" + qualification + "'}";
    }
}
