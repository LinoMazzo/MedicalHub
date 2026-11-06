package com.medicalhub.core.repository;

import com.medicalhub.core.model.RehabPlan;
import java.util.List;
import java.util.Optional;

/**
 * Interfaccia di persistenza per i Piani Riabilitativi.
 */
public interface RehabPlanDatabase {
    void save(RehabPlan plan);
    Optional<RehabPlan> findById(String id);
    List<RehabPlan> findByPatientTaxCode(String patientTaxCode);
    List<RehabPlan> findByDoctorTaxCode(String doctorTaxCode);
    List<RehabPlan> findAll();
    void updateState(String planId, String stateName);
}
