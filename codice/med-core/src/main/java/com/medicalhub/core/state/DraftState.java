package com.medicalhub.core.state;

import com.medicalhub.core.model.RehabPlan;

/**
 * Stato iniziale: il piano è in bozza e non ancora firmato dal medico.
 */
public class DraftState implements RehabPlanState {

    @Override
    public void prescribe(RehabPlan plan) {
        plan.setState(new PrescribedState());
        plan.notifyStateChanged("Il piano è stato ufficialmente prescritto dal medico.");
    }

    @Override
    public void startTreatment(RehabPlan plan) {
        throw new IllegalStateException("Impossibile avviare il trattamento da uno stato di Bozza. Deve prima essere prescritto.");
    }

    @Override
    public void suspend(RehabPlan plan, String reason) {
        throw new IllegalStateException("Impossibile sospendere un piano in Bozza.");
    }

    @Override
    public void completeTreatment(RehabPlan plan) {
        throw new IllegalStateException("Impossibile completare un piano in Bozza.");
    }

    @Override
    public void issueClearance(RehabPlan plan) {
        throw new IllegalStateException("Impossibile rilasciare la clearance su un piano in Bozza.");
    }

    @Override
    public String getStateName() {
        return "Bozza (Draft)";
    }
}
