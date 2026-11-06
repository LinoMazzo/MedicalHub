package com.medicalhub.gui;

import com.formdev.flatlaf.FlatLightLaf;
import javax.swing.*;

/**
 * Entry point del modulo med-gui.
 * Inizializza il tema moderno FlatLaf (FlatLightLaf) prima della creazione dell'interfaccia Dashboard.
 */
public class Main {
    public static void main(String[] args) {
        // Attivazione Tema Moderno Healthcare Desktop (FlatLaf)
        try {
            FlatLightLaf.setup();
            UIManager.put("Button.arc", 12);
            UIManager.put("Component.arc", 12);
            UIManager.put("ProgressBar.arc", 12);
            UIManager.put("TextComponent.arc", 10);
            UIManager.put("Table.rowHeight", 34);
            UIManager.put("Table.showHorizontalLines", true);
            UIManager.put("Table.showVerticalLines", false);
        } catch (Exception e) {
            System.err.println("Impossibile inizializzare il tema FlatLaf: " + e.getMessage());
        }

        SwingUtilities.invokeLater(() -> {
            MedicalHubFrame frame = new MedicalHubFrame();
            frame.setVisible(true);
            System.out.println("MedicalHub Modern Healthcare Dashboard avviata con successo!");
        });
    }
}
