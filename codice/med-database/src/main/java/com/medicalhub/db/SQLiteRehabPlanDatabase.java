package com.medicalhub.db;

import com.medicalhub.core.model.RehabPlan;
import com.medicalhub.core.repository.RehabPlanDatabase;
import com.medicalhub.core.state.*;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Implementazione SQLite del repository RehabPlanDatabase.
 */
public class SQLiteRehabPlanDatabase implements RehabPlanDatabase {
    private final DatabaseConnection dbConn;

    public SQLiteRehabPlanDatabase() {
        this.dbConn = DatabaseConnection.getInstance();
    }

    @Override
    public void save(RehabPlan plan) {
        String sql = "INSERT OR REPLACE INTO rehab_plans (id, patient_tax_code, doctor_tax_code, protocol, start_date, planned_sessions, executed_sessions, state_name) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = dbConn.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, plan.getId());
            stmt.setString(2, plan.getPatientTaxCode());
            stmt.setString(3, plan.getDoctorTaxCode());
            stmt.setString(4, plan.getProtocol());
            stmt.setString(5, plan.getStartDate().toString());
            stmt.setInt(6, plan.getPlannedSessions());
            stmt.setInt(7, plan.getExecutedSessions());
            stmt.setString(8, plan.getStateName());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Errore durante il salvataggio del piano riabilitativo: " + e.getMessage(), e);
        }
    }

    @Override
    public Optional<RehabPlan> findById(String id) {
        String sql = "SELECT * FROM rehab_plans WHERE id = ?";
        try (Connection conn = dbConn.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return Optional.of(mapPlan(rs));
            }
        } catch (SQLException e) {
            System.err.println("Errore ricerca piano per ID: " + e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public List<RehabPlan> findByPatientTaxCode(String patientTaxCode) {
        List<RehabPlan> list = new ArrayList<>();
        String sql = "SELECT * FROM rehab_plans WHERE patient_tax_code = ?";
        try (Connection conn = dbConn.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, patientTaxCode);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                list.add(mapPlan(rs));
            }
        } catch (SQLException e) {
            System.err.println("Errore ricerca piani per paziente: " + e.getMessage());
        }
        return list;
    }

    @Override
    public List<RehabPlan> findByDoctorTaxCode(String doctorTaxCode) {
        List<RehabPlan> list = new ArrayList<>();
        String sql = "SELECT * FROM rehab_plans WHERE doctor_tax_code = ?";
        try (Connection conn = dbConn.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, doctorTaxCode);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                list.add(mapPlan(rs));
            }
        } catch (SQLException e) {
            System.err.println("Errore ricerca piani per medico: " + e.getMessage());
        }
        return list;
    }

    @Override
    public List<RehabPlan> findAll() {
        List<RehabPlan> list = new ArrayList<>();
        String sql = "SELECT * FROM rehab_plans";
        try (Connection conn = dbConn.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                list.add(mapPlan(rs));
            }
        } catch (SQLException e) {
            System.err.println("Errore caricamento lista piani: " + e.getMessage());
        }
        return list;
    }

    @Override
    public void updateState(String planId, String stateName) {
        String sql = "UPDATE rehab_plans SET state_name = ? WHERE id = ?";
        try (Connection conn = dbConn.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, stateName);
            stmt.setString(2, planId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Errore aggiornamento stato piano: " + e.getMessage());
        }
    }

    private RehabPlan mapPlan(ResultSet rs) throws SQLException {
        RehabPlan plan = new RehabPlan(
                rs.getString("id"),
                rs.getString("patient_tax_code"),
                rs.getString("doctor_tax_code"),
                rs.getString("protocol"),
                LocalDate.parse(rs.getString("start_date")),
                rs.getInt("planned_sessions")
        );
        int execSessions = rs.getInt("executed_sessions");
        String countSql = "SELECT COUNT(*) FROM sessions WHERE plan_id = ? AND completed = 1";
        try (PreparedStatement cStmt = dbConn.getConnection().prepareStatement(countSql)) {
            cStmt.setString(1, rs.getString("id"));
            ResultSet cRs = cStmt.executeQuery();
            if (cRs.next()) {
                execSessions = Math.max(execSessions, cRs.getInt(1));
            }
        } catch (SQLException ignored) {}

        plan.setExecutedSessions(execSessions);
        
        String stateStr = rs.getString("state_name");
        if (stateStr.contains("Prescribed")) {
            plan.setState(new PrescribedState());
        } else if (stateStr.contains("InProgress")) {
            plan.setState(new InProgressState());
        } else if (stateStr.contains("Suspended")) {
            plan.setState(new SuspendedState("Ripristinato da DB"));
        } else if (stateStr.contains("Completed")) {
            plan.setState(new CompletedState());
        } else if (stateStr.contains("Cleared")) {
            plan.setState(new ClearedState());
        } else {
            plan.setState(new DraftState());
        }
        return plan;
    }
}
