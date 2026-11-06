package com.medicalhub.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Singleton per la gestione della connessione al database SQLite locale.
 * Al primo avvio esegue automaticamente lo script DDL di creazione delle tabelle se non esistono.
 */
public class DatabaseConnection {
    private static DatabaseConnection instance;
    private Connection connection;
    private static final String DB_URL = "jdbc:sqlite:medicalhub.db";

    private DatabaseConnection() {
        try {
            this.connection = DriverManager.getConnection(DB_URL);
            createTablesIfNotExist();
        } catch (SQLException e) {
            throw new RuntimeException("Errore critico di connessione al database SQLite: " + e.getMessage(), e);
        }
    }

    public static synchronized DatabaseConnection getInstance() {
        if (instance == null || instance.isClosed()) {
            instance = new DatabaseConnection();
        }
        return instance;
    }

    public Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection(DB_URL);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Errore ripristino connessione DB", e);
        }
        return connection;
    }

    private boolean isClosed() {
        try {
            return connection == null || connection.isClosed();
        } catch (SQLException e) {
            return true;
        }
    }

    /**
     * Inizializzazione automatica delle tabelle DDL se assenti.
     */
    private void createTablesIfNotExist() {
        String createPersonTable = """
            CREATE TABLE IF NOT EXISTS persons (
                tax_code VARCHAR(16) PRIMARY KEY,
                first_name VARCHAR(50) NOT NULL,
                last_name VARCHAR(50) NOT NULL,
                birth_date VARCHAR(10) NOT NULL
            );
        """;

        String createRolesTable = """
            CREATE TABLE IF NOT EXISTS person_roles (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                person_tax_code VARCHAR(16) NOT NULL,
                role_type VARCHAR(50) NOT NULL,
                detail_1 TEXT,
                detail_2 TEXT,
                FOREIGN KEY (person_tax_code) REFERENCES persons(tax_code) ON DELETE CASCADE
            );
        """;

        String createPlansTable = """
            CREATE TABLE IF NOT EXISTS rehab_plans (
                id VARCHAR(36) PRIMARY KEY,
                patient_tax_code VARCHAR(16) NOT NULL,
                doctor_tax_code VARCHAR(16) NOT NULL,
                protocol TEXT NOT NULL,
                start_date VARCHAR(10) NOT NULL,
                planned_sessions INTEGER NOT NULL,
                executed_sessions INTEGER NOT NULL,
                state_name VARCHAR(50) NOT NULL,
                FOREIGN KEY (patient_tax_code) REFERENCES persons(tax_code),
                FOREIGN KEY (doctor_tax_code) REFERENCES persons(tax_code)
            );
        """;

        String createSessionsTable = """
            CREATE TABLE IF NOT EXISTS sessions (
                id VARCHAR(36) PRIMARY KEY,
                plan_id VARCHAR(36) NOT NULL,
                date_time VARCHAR(25) NOT NULL,
                physio_tax_code VARCHAR(16) NOT NULL,
                room_id VARCHAR(20) NOT NULL,
                rom_degrees DOUBLE DEFAULT 0.0,
                pain_level INTEGER DEFAULT 0,
                completed BOOLEAN DEFAULT 0,
                clinical_notes TEXT,
                FOREIGN KEY (plan_id) REFERENCES rehab_plans(id)
            );
        """;

        try (Statement stmt = connection.createStatement()) {
            stmt.execute(createPersonTable);
            stmt.execute(createRolesTable);
            stmt.execute(createPlansTable);
            stmt.execute(createSessionsTable);
        } catch (SQLException e) {
            System.err.println("Errore durante la creazione delle tabelle SQLite: " + e.getMessage());
        }
    }
}
