package com.medicalhub.core.state;

import com.medicalhub.core.model.RehabPlan;

/**
 * Il piano riabilitativo è attivo e in fase di esecuzione sedute.
 */
public class InProgressState implements RehabPlanState {

    @Override
    public void prescribe(RehabPlan plan) {
        throw new IllegalStateException("Il piano è già in corso d'opera.");
    }

    @Override
    public void startTreatment(RehabPlan plan) {
        throw new IllegalStateException("Il trattamento è già in corso.");
    }

    @Override
    public void suspend(RehabPlan plan, String reason) {
        plan.setState(new SuspendedState(reason));
        plan.notifyStateChanged("Piano sospeso durante le sedute. Motivo: " + reason);
    }

    @Override
    public void completeTreatment(RehabPlan plan) {
        if (plan.getExecutedSessions() < plan.getPlannedSessions()) {
            throw new IllegalStateException("Impossibile completare: eseguite " + plan.getExecutedSessions() + 
                    " sedute su " + plan.getPlannedSessions() + " pianificate.");
        }
        plan.setState(new CompletedState());
        plan.notifyStateChanged("Tutte le sedute pianificate sono state completate con successo.");
    }

    @Override
    public void issueClearance(RehabPlan plan) {
        throw new IllegalStateException("Prima della clearance medica è necessario completare l'iter delle sedute.");
    }

    @Override
    public String getStateName() {
        return "In Corso (InProgress)";
    }
}
