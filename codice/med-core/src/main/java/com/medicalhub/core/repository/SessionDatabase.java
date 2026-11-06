package com.medicalhub.core.repository;

import com.medicalhub.core.model.Session;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * Interfaccia di persistenza per le Sedute riabilitative.
 */
public interface SessionDatabase {
    void save(Session session);
    Optional<Session> findById(String id);
    List<Session> findByPlanId(String planId);
    List<Session> findByPhysioTaxCode(String physioTaxCode);
    List<Session> findByRoomId(String roomId);
    List<Session> findByDateTimeRange(LocalDateTime start, LocalDateTime end);
    List<Session> findAll();
}
