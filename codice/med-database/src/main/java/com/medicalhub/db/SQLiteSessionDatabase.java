package com.medicalhub.db;

import com.medicalhub.core.model.Session;
import com.medicalhub.core.repository.SessionDatabase;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Implementazione SQLite del repository SessionDatabase.
 */
public class SQLiteSessionDatabase implements SessionDatabase {
    private final DatabaseConnection dbConn;

    public SQLiteSessionDatabase() {
        this.dbConn = DatabaseConnection.getInstance();
    }

    @Override
    public void save(Session session) {
        String sql = "INSERT OR REPLACE INTO sessions (id, plan_id, date_time, physio_tax_code, room_id, rom_degrees, pain_level, completed, clinical_notes) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = dbConn.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, session.getId());
            stmt.setString(2, session.getPlanId());
            stmt.setString(3, session.getDateTime().toString());
            stmt.setString(4, session.getPhysioTaxCode());
            stmt.setString(5, session.getRoomId());
            stmt.setDouble(6, session.getRomDegrees());
            stmt.setInt(7, session.getPainLevel());
            stmt.setBoolean(8, session.isCompleted());
            stmt.setString(9, session.getClinicalNotes());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Errore durante il salvataggio della seduta SQLite: " + e.getMessage(), e);
        }
    }

    @Override
    public Optional<Session> findById(String id) {
        String sql = "SELECT * FROM sessions WHERE id = ?";
        try (Connection conn = dbConn.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return Optional.of(mapSession(rs));
            }
        } catch (SQLException e) {
            System.err.println("Errore ricerca seduta per ID: " + e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public List<Session> findByPlanId(String planId) {
        List<Session> list = new ArrayList<>();
        String sql = "SELECT * FROM sessions WHERE plan_id = ?";
        try (Connection conn = dbConn.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, planId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                list.add(mapSession(rs));
            }
        } catch (SQLException e) {
            System.err.println("Errore ricerca sedute per piano: " + e.getMessage());
        }
        return list;
    }

    @Override
    public List<Session> findByPhysioTaxCode(String physioTaxCode) {
        List<Session> list = new ArrayList<>();
        String sql = "SELECT * FROM sessions WHERE physio_tax_code = ?";
        try (Connection conn = dbConn.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, physioTaxCode);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                list.add(mapSession(rs));
            }
        } catch (SQLException e) {
            System.err.println("Errore ricerca sedute per fisioterapista: " + e.getMessage());
        }
        return list;
    }

    @Override
    public List<Session> findByRoomId(String roomId) {
        List<Session> list = new ArrayList<>();
        String sql = "SELECT * FROM sessions WHERE room_id = ?";
        try (Connection conn = dbConn.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, roomId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                list.add(mapSession(rs));
            }
        } catch (SQLException e) {
            System.err.println("Errore ricerca sedute per stanza: " + e.getMessage());
        }
        return list;
    }

    @Override
    public List<Session> findByDateTimeRange(LocalDateTime start, LocalDateTime end) {
        List<Session> list = new ArrayList<>();
        String sql = "SELECT * FROM sessions WHERE date_time >= ? AND date_time <= ?";
        try (Connection conn = dbConn.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, start.toString());
            stmt.setString(2, end.toString());
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                list.add(mapSession(rs));
            }
        } catch (SQLException e) {
            System.err.println("Errore ricerca sedute per intervallo data/ora: " + e.getMessage());
        }
        return list;
    }

    @Override
    public List<Session> findAll() {
        List<Session> list = new ArrayList<>();
        String sql = "SELECT * FROM sessions";
        try (Connection conn = dbConn.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                list.add(mapSession(rs));
            }
        } catch (SQLException e) {
            System.err.println("Errore ricerca lista sedute: " + e.getMessage());
        }
        return list;
    }

    private Session mapSession(ResultSet rs) throws SQLException {
        Session session = new Session(
                rs.getString("id"),
                rs.getString("plan_id"),
                LocalDateTime.parse(rs.getString("date_time")),
                rs.getString("physio_tax_code"),
                rs.getString("room_id")
        );
        if (rs.getBoolean("completed")) {
            session.recordOutcome(
                    rs.getDouble("rom_degrees"),
                    rs.getInt("pain_level"),
                    rs.getString("clinical_notes")
            );
        }
        return session;
    }
}
