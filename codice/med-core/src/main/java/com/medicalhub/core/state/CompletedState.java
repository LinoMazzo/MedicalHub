package com.medicalhub.core.state;

import com.medicalhub.core.model.RehabPlan;

/**
 * Tutte le sedute sono state svolte. Il piano attende la valutazione clinica finale del medico per l'idoneità.
 */
public class CompletedState implements RehabPlanState {

    @Override
    public void prescribe(RehabPlan plan) {
        throw new IllegalStateException("Il piano è già completato.");
    }

    @Override
    public void startTreatment(RehabPlan plan) {
        throw new IllegalStateException("Le sedute sono già state tutte svolte.");
    }

    @Override
    public void suspend(RehabPlan plan, String reason) {
        throw new IllegalStateException("Impossibile sospendere un piano già completato.");
    }

    @Override
    public void completeTreatment(RehabPlan plan) {
        throw new IllegalStateException("Il piano è già in stato completato.");
    }

    @Override
    public void issueClearance(RehabPlan plan) {
        plan.setState(new ClearedState());
        plan.notifyStateChanged("Valutazione favorevole: Rilasciata idoneità sportiva/lavorativa finale (Clearance).");
    }

    @Override
    public String getStateName() {
        return "Completato (Completed)";
    }
}
