package com.medicalhub.core.state;

import com.medicalhub.core.model.RehabPlan;

/**
 * Stato finale: Idoneità/Clearance rilasciata. Il ciclo riabilitativo è terminato con successo.
 */
public class ClearedState implements RehabPlanState {

    @Override
    public void prescribe(RehabPlan plan) {
        throw new IllegalStateException("Stato finale (Cleared): nessuna ulteriore azione permessa.");
    }

    @Override
    public void startTreatment(RehabPlan plan) {
        throw new IllegalStateException("Stato finale (Cleared): il piano è concluso.");
    }

    @Override
    public void suspend(RehabPlan plan, String reason) {
        throw new IllegalStateException("Stato finale (Cleared): il piano è concluso.");
    }

    @Override
    public void completeTreatment(RehabPlan plan) {
        throw new IllegalStateException("Stato finale (Cleared): il piano è concluso.");
    }

    @Override
    public void issueClearance(RehabPlan plan) {
        throw new IllegalStateException("Clearance già rilasciata in precedenza.");
    }

    @Override
    public String getStateName() {
        return "Idoneità Rilasciata (Cleared)";
    }
}
