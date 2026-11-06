package com.medicalhub.core.service;

import com.medicalhub.core.model.Person;
import com.medicalhub.core.model.RehabPlan;
import com.medicalhub.core.model.role.DoctorRole;
import com.medicalhub.core.model.role.PatientRole;
import com.medicalhub.core.repository.PersonDatabase;
import com.medicalhub.core.repository.RehabPlanDatabase;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

/**
 * Servizio per la gestione e l'avanzamento dei Piani Riabilitativi.
 */
public class RehabPlanService {
    private final RehabPlanDatabase planDb;
    private final PersonDatabase personDb;

    public RehabPlanService(RehabPlanDatabase planDb, PersonDatabase personDb) {
        this.planDb = planDb;
        this.personDb = personDb;
    }

    /**
     * Crea un nuovo piano riabilitativo in stato di Bozza verificando l'esistenza dei ruoli.
     */
    public RehabPlan createPlan(String patientTaxCode, String doctorTaxCode, String protocol, LocalDate startDate, int plannedSessions) {
        Person patient = personDb.findByTaxCode(patientTaxCode)
                .orElseThrow(() -> new IllegalArgumentException("Paziente non trovato con codice fiscale: " + patientTaxCode));
        if (!patient.hasRole(PatientRole.class)) {
            throw new IllegalArgumentException("La persona " + patientTaxCode + " non possiede il ruolo di Paziente");
        }

        Person doctor = personDb.findByTaxCode(doctorTaxCode)
                .orElseThrow(() -> new IllegalArgumentException("Medico non trovato con codice fiscale: " + doctorTaxCode));
        if (!doctor.hasRole(DoctorRole.class)) {
            throw new IllegalArgumentException("La persona " + doctorTaxCode + " non possiede il ruolo di Medico");
        }

        String planId = "PLAN-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        RehabPlan plan = new RehabPlan(planId, patientTaxCode, doctorTaxCode, protocol, startDate, plannedSessions);
        planDb.save(plan);
        return plan;
    }

    public void prescribePlan(String planId) {
        RehabPlan plan = getPlanOrThrow(planId);
        plan.prescribe();
        planDb.save(plan);
    }

    public void startTreatment(String planId) {
        RehabPlan plan = getPlanOrThrow(planId);
        plan.startTreatment();
        planDb.save(plan);
    }

    public void suspendPlan(String planId, String reason) {
        RehabPlan plan = getPlanOrThrow(planId);
        plan.suspend(reason);
        planDb.save(plan);
    }

    public void completeTreatment(String planId) {
        RehabPlan plan = getPlanOrThrow(planId);
        plan.completeTreatment();
        planDb.save(plan);
    }

    public void issueClearance(String planId) {
        RehabPlan plan = getPlanOrThrow(planId);
        plan.issueClearance();
        planDb.save(plan);
    }

    public RehabPlan getPlanOrThrow(String planId) {
        return planDb.findById(planId)
                .orElseThrow(() -> new IllegalArgumentException("Piano riabilitativo non trovato: " + planId));
    }

    public List<RehabPlan> getAllPlans() {
        return planDb.findAll();
    }
}
