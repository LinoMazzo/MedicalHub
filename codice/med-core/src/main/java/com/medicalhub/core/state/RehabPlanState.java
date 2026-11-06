package com.medicalhub.core.state;

import com.medicalhub.core.model.RehabPlan;

/**
 * Interfaccia dello Stato nel ciclo di vita di un piano riabilitativo.
 */
public interface RehabPlanState {
    void prescribe(RehabPlan plan);
    void startTreatment(RehabPlan plan);
    void suspend(RehabPlan plan, String reason);
    void completeTreatment(RehabPlan plan);
    void issueClearance(RehabPlan plan);
    String getStateName();
}
