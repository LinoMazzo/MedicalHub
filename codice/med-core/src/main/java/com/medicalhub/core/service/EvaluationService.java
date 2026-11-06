package com.medicalhub.core.service;

import com.medicalhub.core.model.RehabPlan;
import com.medicalhub.core.model.Session;
import com.medicalhub.core.observer.NotificationEvent;
import com.medicalhub.core.repository.RehabPlanDatabase;
import com.medicalhub.core.repository.SessionDatabase;

import java.util.List;

/**
 * Servizio per la valutazione clinica quantitativa del recupero funzionale del paziente.
 */
public class EvaluationService {
    private final SessionDatabase sessionDb;
    private final RehabPlanDatabase planDb;

    public EvaluationService(SessionDatabase sessionDb, RehabPlanDatabase planDb) {
        this.sessionDb = sessionDb;
        this.planDb = planDb;
    }

    public EvaluationService(SessionDatabase sessionDb) {
        this(sessionDb, null);
    }

    /**
     * Calcola la percentuale di recupero in base ai ROM registrati e al dolore medio.
     * @param targetRom gradi ROM obiettivo per l'articolazione (es. 120 gradi per ginocchio)
     */
    public double calculateRecoveryPercentage(RehabPlan plan, double targetRom) {
        List<Session> sessions = sessionDb.findByPlanId(plan.getId());
        if (sessions.isEmpty()) {
            return 0.0;
        }

        double maxRomRecorded = sessions.stream()
                .filter(Session::isCompleted)
                .mapToDouble(Session::getRomDegrees)
                .max()
                .orElse(0.0);

        double avgPainLevel = sessions.stream()
                .filter(Session::isCompleted)
                .mapToInt(Session::getPainLevel)
                .average()
                .orElse(10.0);

        double romFactor = Math.min(1.0, maxRomRecorded / targetRom);
        double painFactor = (10.0 - avgPainLevel) / 10.0;

        // Formula pesata: 70% incremento ROM + 30% riduzione dolore
        double recovery = (romFactor * 70.0) + (painFactor * 30.0);
        return Math.round(recovery * 100.0) / 100.0;
    }

    /**
     * Valuta l'esito della seduta e genera un alert al medico in caso di dolore critico (>= 8).
     */
    public void recordAndEvaluateSessionOutcome(RehabPlan plan, Session session, double romDegrees, int painLevel, String notes) {
        session.recordOutcome(romDegrees, painLevel, notes);
        sessionDb.save(session);
        plan.incrementExecutedSessions();
        if (planDb != null) {
            planDb.save(plan);
        }

        if (painLevel >= 8) {
            plan.notifyObservers(new NotificationEvent(
                    NotificationEvent.EventType.CRITICAL_SESSION_OUTCOME,
                    plan.getId(),
                    "ATTENZIONE: Registrato livello di DOLORE SEVERO (" + painLevel + "/10) nella seduta del " + session.getDateTime() + ". Note: " + notes
            ));
        }
    }
}
