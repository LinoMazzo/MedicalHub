package com.medicalhub.core.state;

import com.medicalhub.core.model.RehabPlan;

/**
 * Il piano è stato prescritto dal medico ed è pronto per iniziare le sedute.
 */
public class PrescribedState implements RehabPlanState {

    @Override
    public void prescribe(RehabPlan plan) {
        throw new IllegalStateException("Il piano è già stato prescritto.");
    }

    @Override
    public void startTreatment(RehabPlan plan) {
        plan.setState(new InProgressState());
        plan.notifyStateChanged("Trattamento riabilitativo avviato. Inizio sedute.");
    }

    @Override
    public void suspend(RehabPlan plan, String reason) {
        plan.setState(new SuspendedState(reason));
        plan.notifyStateChanged("Piano prescritto sospeso. Motivo: " + reason);
    }

    @Override
    public void completeTreatment(RehabPlan plan) {
        throw new IllegalStateException("Impossibile completare il trattamento prima di aver eseguito le sedute.");
    }

    @Override
    public void issueClearance(RehabPlan plan) {
        throw new IllegalStateException("Impossibile rilasciare idoneità/clearance su un piano prescritto non ancora svolto.");
    }

    @Override
    public String getStateName() {
        return "Prescritto (Prescribed)";
    }
}
