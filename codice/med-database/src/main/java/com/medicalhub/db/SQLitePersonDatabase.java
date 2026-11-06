package com.medicalhub.db;

import com.medicalhub.core.model.Person;
import com.medicalhub.core.model.role.*;
import com.medicalhub.core.repository.PersonDatabase;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Implementazione SQLite del repository PersonDatabase.
 */
public class SQLitePersonDatabase implements PersonDatabase {
    private final DatabaseConnection dbConn;

    public SQLitePersonDatabase() {
        this.dbConn = DatabaseConnection.getInstance();
    }

    @Override
    public void save(Person person) {
        String upsertPerson = "INSERT OR REPLACE INTO persons (tax_code, first_name, last_name, birth_date) VALUES (?, ?, ?, ?)";
        String deleteRoles = "DELETE FROM person_roles WHERE person_tax_code = ?";
        String insertRole = "INSERT INTO person_roles (person_tax_code, role_type, detail_1, detail_2) VALUES (?, ?, ?, ?)";

        try (Connection conn = dbConn.getConnection()) {
            conn.setAutoCommit(false);
            try (PreparedStatement pStmt = conn.prepareStatement(upsertPerson)) {
                pStmt.setString(1, person.getTaxCode());
                pStmt.setString(2, person.getFirstName());
                pStmt.setString(3, person.getLastName());
                pStmt.setString(4, person.getBirthDate().toString());
                pStmt.executeUpdate();
            }

            try (PreparedStatement dStmt = conn.prepareStatement(deleteRoles)) {
                dStmt.setString(1, person.getTaxCode());
                dStmt.executeUpdate();
            }

            for (UserRole role : person.getRoles()) {
                try (PreparedStatement rStmt = conn.prepareStatement(insertRole)) {
                    rStmt.setString(1, person.getTaxCode());
                    if (role instanceof PatientRole pr) {
                        rStmt.setString(2, "PATIENT");
                        rStmt.setString(3, pr.getSport());
                        rStmt.setString(4, pr.getInjuryHistory());
                    } else if (role instanceof DoctorRole dr) {
                        rStmt.setString(2, "DOCTOR");
                        rStmt.setString(3, dr.getRegistrationNumber());
                        rStmt.setString(4, dr.getSpecialization());
                    } else if (role instanceof PhysiotherapistRole ptr) {
                        rStmt.setString(2, "PHYSIO");
                        rStmt.setString(3, ptr.getQualification());
                        rStmt.setString(4, "");
                    }
                    rStmt.executeUpdate();
                }
            }
            conn.commit();
        } catch (SQLException e) {
            throw new RuntimeException("Errore durante il salvataggio della persona nel DB SQLite: " + e.getMessage(), e);
        }
    }

    @Override
    public Optional<Person> findByTaxCode(String taxCode) {
        String sql = "SELECT * FROM persons WHERE tax_code = ?";
        try (Connection conn = dbConn.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, taxCode);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Person person = mapPerson(rs);
                loadRoles(person, conn);
                return Optional.of(person);
            }
        } catch (SQLException e) {
            System.err.println("Errore ricerca persona per codice fiscale: " + e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public List<Person> findAll() {
        List<Person> list = new ArrayList<>();
        String sql = "SELECT * FROM persons";
        try (Connection conn = dbConn.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Person person = mapPerson(rs);
                loadRoles(person, conn);
                list.add(person);
            }
        } catch (SQLException e) {
            System.err.println("Errore caricamento lista persone: " + e.getMessage());
        }
        return list;
    }

    @Override
    public List<Person> findByRoleClass(Class<?> roleClass) {
        List<Person> all = findAll();
        return all.stream()
                .filter(p -> p.getRoles().stream().anyMatch(r -> roleClass.isAssignableFrom(r.getClass())))
                .toList();
    }

    @Override
    public void deleteByTaxCode(String taxCode) {
        String sql = "DELETE FROM persons WHERE tax_code = ?";
        try (Connection conn = dbConn.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, taxCode);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Errore eliminazione persona: " + e.getMessage());
        }
    }

    private Person mapPerson(ResultSet rs) throws SQLException {
        return new Person(
                rs.getString("tax_code"),
                rs.getString("first_name"),
                rs.getString("last_name"),
                LocalDate.parse(rs.getString("birth_date"))
        );
    }

    private void loadRoles(Person person, Connection conn) throws SQLException {
        String sql = "SELECT * FROM person_roles WHERE person_tax_code = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, person.getTaxCode());
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String type = rs.getString("role_type");
                String d1 = rs.getString("detail_1");
                String d2 = rs.getString("detail_2");

                if ("PATIENT".equals(type)) {
                    person.addRole(new PatientRole(d1, d2));
                } else if ("DOCTOR".equals(type)) {
                    person.addRole(new DoctorRole(d1, d2));
                } else if ("PHYSIO".equals(type)) {
                    person.addRole(new PhysiotherapistRole(d1));
                }
            }
        }
    }
}
