package com.medicalhub.core.service;

import com.medicalhub.core.model.Person;
import com.medicalhub.core.model.RehabPlan;
import com.medicalhub.core.model.Session;
import com.medicalhub.core.model.role.PhysiotherapistRole;
import com.medicalhub.core.observer.NotificationEvent;
import com.medicalhub.core.repository.PersonDatabase;
import com.medicalhub.core.repository.RehabPlanDatabase;
import com.medicalhub.core.repository.SessionDatabase;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * Servizio per la prenotazione delle sedute con controllo rigoroso dei conflitti orari.
 */
public class BookingService {
    private final SessionDatabase sessionDb;
    private final RehabPlanDatabase planDb;
    private final PersonDatabase personDb;

    public BookingService(SessionDatabase sessionDb, RehabPlanDatabase planDb, PersonDatabase personDb) {
        this.sessionDb = sessionDb;
        this.planDb = planDb;
        this.personDb = personDb;
    }

    /**
     * Prenota una nuova seduta previa verifica dei conflitti su Stanza e Fisioterapista.
     */
    public Session bookSession(String planId, LocalDateTime dateTime, String physioTaxCode, String roomId) {
        RehabPlan plan = planDb.findById(planId)
                .orElseThrow(() -> new IllegalArgumentException("Piano non trovato: " + planId));

        Person physio = personDb.findByTaxCode(physioTaxCode)
                .orElseThrow(() -> new IllegalArgumentException("Fisioterapista non trovato: " + physioTaxCode));
        if (!physio.hasRole(PhysiotherapistRole.class)) {
            throw new IllegalArgumentException("La persona " + physioTaxCode + " non è un Fisioterapista qualificato");
        }

        // Controllo Conflitti
        if (hasConflict(roomId, physioTaxCode, dateTime)) {
            throw new IllegalStateException("CONFLITTO DI PRENOTAZIONE: La stanza '" + roomId + 
                    "' o il fisioterapista '" + physio.getFullName() + 
                    "' sono già impegnati alla data e ora: " + dateTime);
        }

        String sessionId = "SESS-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        Session session = new Session(sessionId, planId, dateTime, physioTaxCode, roomId);
        sessionDb.save(session);
        plan.addSession(session);

        // Notifica
        plan.notifyObservers(new NotificationEvent(
                NotificationEvent.EventType.SESSION_BOOKED,
                planId,
                "Seduta prenotata per il " + dateTime + " con " + physio.getFullName() + " nella stanza " + roomId
        ));

        return session;
    }

    /**
     * Verifica la presenza di sovrapposizioni orari (stesso slot orario entro 45 minuti).
     */
    public boolean hasConflict(String roomId, String physioTaxCode, LocalDateTime dateTime) {
        List<Session> allSessions = sessionDb.findAll();
        for (Session existing : allSessions) {
            // Verifica tolleranza di 45 minuti tra sedute
            long minutesDiff = Math.abs(java.time.Duration.between(existing.getDateTime(), dateTime).toMinutes());
            if (minutesDiff < 45) {
                if (existing.getRoomId().equalsIgnoreCase(roomId)) {
                    return true; // Conflitto Stanza
                }
                if (existing.getPhysioTaxCode().equalsIgnoreCase(physioTaxCode)) {
                    return true; // Conflitto Fisioterapista
                }
            }
        }
        return false;
    }
}
