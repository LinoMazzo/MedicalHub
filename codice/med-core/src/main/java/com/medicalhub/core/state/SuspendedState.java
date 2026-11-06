package com.medicalhub.core.state;

import com.medicalhub.core.model.RehabPlan;

/**
 * Il piano è stato sospeso per complicanze cliniche o motivi personali del paziente.
 */
public class SuspendedState implements RehabPlanState {
    private final String reason;

    public SuspendedState(String reason) {
        this.reason = reason != null ? reason : "Motivo non specificato";
    }

    @Override
    public void prescribe(RehabPlan plan) {
        throw new IllegalStateException("Il piano è sospeso. Impossibile ri-prescrivere.");
    }

    @Override
    public void startTreatment(RehabPlan plan) {
        plan.setState(new InProgressState());
        plan.notifyStateChanged("Piano ripreso dalla sospensione.");
    }

    @Override
    public void suspend(RehabPlan plan, String reason) {
        throw new IllegalStateException("Il piano è già in stato sospeso.");
    }

    @Override
    public void completeTreatment(RehabPlan plan) {
        throw new IllegalStateException("Impossibile completare un piano sospeso senza prima riprenderlo.");
    }

    @Override
    public void issueClearance(RehabPlan plan) {
        throw new IllegalStateException("Impossibile rilasciare clearance su piano sospeso.");
    }

    @Override
    public String getStateName() {
        return "Sospeso (Suspended: " + reason + ")";
    }

    public String getReason() {
        return reason;
    }
}
