package com.medicalhub.gui;

import com.medicalhub.core.model.Person;
import com.medicalhub.core.model.RehabPlan;
import com.medicalhub.core.model.Session;
import com.medicalhub.core.model.role.DoctorRole;
import com.medicalhub.core.model.role.PatientRole;
import com.medicalhub.core.model.role.PhysiotherapistRole;
import com.medicalhub.core.observer.DoctorAlertNotifier;
import com.medicalhub.core.observer.PatientReminderNotifier;
import com.medicalhub.core.service.BookingService;
import com.medicalhub.core.service.EvaluationService;
import com.medicalhub.core.service.RehabPlanService;
import com.medicalhub.db.DatabaseConnection;
import com.medicalhub.db.SQLitePersonDatabase;
import com.medicalhub.db.SQLiteRehabPlanDatabase;
import com.medicalhub.db.SQLiteSessionDatabase;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Interfaccia grafica Desktop Moderna in Java Swing - MedicalHub (FlatLaf Healthcare Dashboard).
 * Layout a Sidebar fissa Deep Navy con CardLayout per la navigazione dinamica senza tab classiche.
 */
public class MedicalHubFrame extends JFrame {
    private final SQLitePersonDatabase personDb;
    private final SQLiteRehabPlanDatabase planDb;
    private final SQLiteSessionDatabase sessionDb;

    private final RehabPlanService planService;
    private final BookingService bookingService;
    private final EvaluationService evaluationService;

    private final DoctorAlertNotifier doctorNotifier;
    private final PatientReminderNotifier patientNotifier;

    // CardLayout Container
    private CardLayout cardLayout;
    private JPanel mainCardPanel;

    // Sidebar Nav Buttons
    private JButton btnNavPersons, btnNavPlans, btnNavSessions, btnNavDb;

    // Form 1 UI (Pazienti & Personale)
    private JTextField txtTaxCode, txtFirstName, txtLastName, txtBirthDate;
    private JCheckBox chkPatient, chkDoctor, chkPhysio;
    private JTextField txtSport, txtInjury, txtRegNum, txtSpec, txtQual;
    private JTextArea txtPersonLog;

    // Form 2 UI (Piani Riabilitativi)
    private JComboBox<String> cbPatientSelect, cbDoctorSelect;
    private JTextField txtProtocol, txtPlannedSessions;
    private JComboBox<String> cbPlanSelect;
    private JTextArea txtPlanDetails, txtObserverLog;

    // Form 3 UI (Calendario Sedute & Clinica)
    private JComboBox<String> cbBookingPlanSelect, cbPhysioSelect, cbRoomSelect;
    private JTextField txtDateTime, txtRom;
    private JSlider sliderPain;
    private JLabel lblPainValue;
    private JTextArea txtSessionNotes, txtOutcomeLog;

    // Form 4 UI (Viewer SQLite)
    private JTable tablePersons, tablePlans, tableSessions;
    private DefaultTableModel modelPersons, modelPlans, modelSessions;

    // Palette Colori Healthcare Dashboard
    private static final Color COLOR_SIDEBAR_BG = new Color(15, 23, 42);      // Deep Navy (#0F172A)
    private static final Color COLOR_SIDEBAR_ACTIVE = new Color(14, 165, 233); // Cyan/Teal Accent (#0EA5E9)
    private static final Color COLOR_MAIN_BG = new Color(248, 250, 252);     // Soft Gray (#F8FAFC)
    private static final Color COLOR_CARD_BG = new Color(255, 255, 255);      // Pure White (#FFFFFF)
    private static final Color COLOR_TEXT_DARK = new Color(15, 23, 42);       // Slate 900 (#0F172A)
    private static final Color COLOR_TEXT_MUTED = new Color(100, 116, 139);   // Slate 500 (#64748B)
    private static final Color COLOR_BORDER = new Color(226, 232, 240);       // Soft Gray Border (#E2E8F0)
    private static final Color COLOR_PRIMARY = new Color(14, 148, 136);       // Teal 600 (#0D9488)

    private static final Font FONT_SIDEBAR_HEADER = new Font("Segoe UI", Font.BOLD, 17);
    private static final Font FONT_NAV = new Font("Segoe UI", Font.BOLD, 13);
    private static final Font FONT_TITLE = new Font("Segoe UI", Font.BOLD, 14);
    private static final Font FONT_PLAIN = new Font("Segoe UI", Font.PLAIN, 12);
    private static final Font FONT_MONO = new Font("Consolas", Font.PLAIN, 12);

    public MedicalHubFrame() {
        super("MedicalHub - Sistema di Gestione Riabilitativa (Healthcare Desktop Dashboard)");

        this.personDb = new SQLitePersonDatabase();
        this.planDb = new SQLiteRehabPlanDatabase();
        this.sessionDb = new SQLiteSessionDatabase();

        this.planService = new RehabPlanService(planDb, personDb);
        this.bookingService = new BookingService(sessionDb, planDb, personDb);
        this.evaluationService = new EvaluationService(sessionDb, planDb);

        this.doctorNotifier = new DoctorAlertNotifier();
        this.patientNotifier = new PatientReminderNotifier();

        initUI();
    }

    private void initUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1240, 840);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // 1. SIDEBAR NAVIGATION (Sinistra - Deep Navy)
        JPanel sidebar = createSidebar();
        add(sidebar, BorderLayout.WEST);

        // 2. MAIN CONTENT AREA (Centro/Destra - CardLayout)
        cardLayout = new CardLayout();
        mainCardPanel = new JPanel(cardLayout);
        mainCardPanel.setBackground(COLOR_MAIN_BG);

        mainCardPanel.add(createPersonPanel(), "CARD_PERSONS");
        mainCardPanel.add(createPlanPanel(), "CARD_PLANS");
        mainCardPanel.add(createSessionPanel(), "CARD_SESSIONS");
        mainCardPanel.add(createDatabaseViewerPanel(), "CARD_DB");

        add(mainCardPanel, BorderLayout.CENTER);

        refreshDropdowns();
        refreshDatabaseTables();
        switchCard("CARD_PERSONS", btnNavPersons);
    }

    private JPanel createSidebar() {
        JPanel sidebar = new JPanel(new BorderLayout());
        sidebar.setPreferredSize(new Dimension(250, 0));
        sidebar.setBackground(COLOR_SIDEBAR_BG);
        sidebar.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, new Color(30, 41, 59)));

        // Header Sidebar: Logo & Titolo
        JPanel header = new JPanel();
        header.setLayout(new BoxLayout(header, BoxLayout.Y_AXIS));
        header.setBackground(COLOR_SIDEBAR_BG);
        header.setBorder(new EmptyBorder(24, 20, 24, 20));

        JLabel logoLabel = new JLabel("🏥 MedicalHub");
        logoLabel.setFont(FONT_SIDEBAR_HEADER);
        logoLabel.setForeground(Color.WHITE);
        logoLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel subLabel = new JLabel("Healthcare Dashboard v1.0");
        subLabel.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        subLabel.setForeground(COLOR_SIDEBAR_ACTIVE);
        subLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel dbStatus = new JLabel("● SQLite Connected");
        dbStatus.setFont(new Font("Segoe UI", Font.BOLD, 10));
        dbStatus.setForeground(new Color(52, 211, 153));
        dbStatus.setBorder(new EmptyBorder(8, 0, 0, 0));
        dbStatus.setAlignmentX(Component.LEFT_ALIGNMENT);

        header.add(logoLabel);
        header.add(Box.createRigidArea(new Dimension(0, 4)));
        header.add(subLabel);
        header.add(dbStatus);

        // Menu di Navigazione Verticale
        JPanel navMenu = new JPanel();
        navMenu.setLayout(new GridLayout(6, 1, 0, 8));
        navMenu.setBackground(COLOR_SIDEBAR_BG);
        navMenu.setBorder(new EmptyBorder(10, 12, 10, 12));

        btnNavPersons = createNavButton("👤  Pazienti & Personale", "CARD_PERSONS");
        btnNavPlans = createNavButton("🔄  Piani Riabilitativi", "CARD_PLANS");
        btnNavSessions = createNavButton("📅  Calendario Sedute", "CARD_SESSIONS");
        btnNavDb = createNavButton("🗄️  Database SQLite", "CARD_DB");

        navMenu.add(btnNavPersons);
        navMenu.add(btnNavPlans);
        navMenu.add(btnNavSessions);
        navMenu.add(btnNavDb);

        // Footer Sidebar: Utente
        JPanel footer = new JPanel(new BorderLayout());
        footer.setBackground(new Color(30, 41, 59));
        footer.setBorder(new EmptyBorder(12, 16, 12, 16));

        JLabel userLabel = new JLabel("Dr. Marco Rossi");
        userLabel.setFont(new Font("Segoe UI", Font.BOLD, 12));
        userLabel.setForeground(Color.WHITE);

        JLabel roleLabel = new JLabel("Medico Prescrittore");
        roleLabel.setFont(new Font("Segoe UI", Font.PLAIN, 10));
        roleLabel.setForeground(new Color(148, 163, 184));

        footer.add(userLabel, BorderLayout.NORTH);
        footer.add(roleLabel, BorderLayout.SOUTH);

        sidebar.add(header, BorderLayout.NORTH);
        sidebar.add(navMenu, BorderLayout.CENTER);
        sidebar.add(footer, BorderLayout.SOUTH);

        return sidebar;
    }

    private JButton createNavButton(String text, String cardName) {
        JButton btn = new JButton(text);
        btn.setFont(FONT_NAV);
        btn.setForeground(Color.WHITE);
        btn.setBackground(COLOR_SIDEBAR_BG);
        btn.setHorizontalAlignment(SwingConstants.LEFT);
        btn.setBorder(new EmptyBorder(10, 16, 10, 16));
        btn.setFocusPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.putClientProperty("JComponent.roundRect", true);

        btn.addActionListener(e -> switchCard(cardName, btn));
        return btn;
    }

    private void switchCard(String cardName, JButton activeBtn) {
        cardLayout.show(mainCardPanel, cardName);

        JButton[] buttons = {btnNavPersons, btnNavPlans, btnNavSessions, btnNavDb};
        for (JButton b : buttons) {
            if (b != null) {
                b.setBackground(COLOR_SIDEBAR_BG);
                b.setForeground(Color.WHITE);
            }
        }
        if (activeBtn != null) {
            activeBtn.setBackground(COLOR_SIDEBAR_ACTIVE);
            activeBtn.setForeground(Color.WHITE);
        }
    }

    // --- SCHERMATA 1: PAZIENTI & PERSONALE ---
    private JPanel createPersonPanel() {
        JPanel container = new JPanel(new BorderLayout(16, 16));
        container.setBackground(COLOR_MAIN_BG);
        container.setBorder(new EmptyBorder(20, 20, 20, 20));

        container.add(createScreenHeader("👤 Gestione Anagrafica e Polimorfismo Player-Role",
                "Registra persone e assegna ruoli dinamici (Paziente, Medico Prescrittore, Fisioterapista)"), BorderLayout.NORTH);

        JPanel mainGrid = new JPanel(new GridLayout(1, 2, 16, 16));
        mainGrid.setBackground(COLOR_MAIN_BG);

        // Card Form Sinistra
        JPanel formCard = new JPanel(new BorderLayout(12, 12));
        formCard.setBackground(COLOR_CARD_BG);
        formCard.setBorder(createCardBorder("Nuova Persona (Entità Player)"));

        JPanel formFields = new JPanel(new GridLayout(10, 2, 8, 8));
        formFields.setBackground(COLOR_CARD_BG);

        txtTaxCode = createStyledTextField("RSSMRA85M01H501Z");
        txtFirstName = createStyledTextField("Mario");
        txtLastName = createStyledTextField("Rossi");
        txtBirthDate = createStyledTextField("1985-05-15");

        chkPatient = createStyledCheckBox("Ruolo Paziente", true);
        txtSport = createStyledTextField("Calcio");
        txtInjury = createStyledTextField("Lesione Legamento Crociato Anteriore");

        chkDoctor = createStyledCheckBox("Ruolo Medico Prescrittore", false);
        txtRegNum = createStyledTextField("DOC-12345");
        txtSpec = createStyledTextField("Fisiatria e Ortopedia");

        chkPhysio = createStyledCheckBox("Ruolo Fisioterapista", false);
        txtQual = createStyledTextField("Terapia Manuale Kinesiologica");

        formFields.add(createDarkLabel("Codice Fiscale:")); formFields.add(txtTaxCode);
        formFields.add(createDarkLabel("Nome:")); formFields.add(txtFirstName);
        formFields.add(createDarkLabel("Cognome:")); formFields.add(txtLastName);
        formFields.add(createDarkLabel("Data Nascita (AAAA-MM-GG):")); formFields.add(txtBirthDate);
        formFields.add(chkPatient); formFields.add(new JLabel(""));
        formFields.add(createDarkLabel("Sport / Anamnesi:")); formFields.add(txtSport);
        formFields.add(chkDoctor); formFields.add(new JLabel(""));
        formFields.add(createDarkLabel("Albo / Spec:")); formFields.add(txtSpec);
        formFields.add(chkPhysio); formFields.add(new JLabel(""));
        formFields.add(createDarkLabel("Qualifica Fisioterapia:")); formFields.add(txtQual);

        JButton btnSavePerson = createPrimaryButton("💾  Salva Persona in SQLite");
        btnSavePerson.addActionListener(e -> savePersonAction());

        formCard.add(formFields, BorderLayout.CENTER);
        formCard.add(btnSavePerson, BorderLayout.SOUTH);

        // Card Log Destra
        JPanel logCard = new JPanel(new BorderLayout(8, 8));
        logCard.setBackground(COLOR_CARD_BG);
        logCard.setBorder(createCardBorder("Registro Eventi & Feedback SQLite"));

        txtPersonLog = createDarkTextArea();
        logCard.add(new JScrollPane(txtPersonLog), BorderLayout.CENTER);

        mainGrid.add(formCard);
        mainGrid.add(logCard);

        container.add(mainGrid, BorderLayout.CENTER);
        return container;
    }

    private void savePersonAction() {
        try {
            Person person = new Person(
                    txtTaxCode.getText(),
                    txtFirstName.getText(),
                    txtLastName.getText(),
                    LocalDate.parse(txtBirthDate.getText())
            );

            if (chkPatient.isSelected()) {
                person.addRole(new PatientRole(txtSport.getText(), txtInjury.getText()));
            }
            if (chkDoctor.isSelected()) {
                person.addRole(new DoctorRole(txtRegNum.getText(), txtSpec.getText()));
            }
            if (chkPhysio.isSelected()) {
                person.addRole(new PhysiotherapistRole(txtQual.getText()));
            }

            personDb.save(person);
            txtPersonLog.append("SUCCESS: Salvata persona " + person.getFullName() + " (" + person.getTaxCode() + ") con " + person.getRoles().size() + " ruoli attivi.");
            refreshDropdowns();
            refreshDatabaseTables();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Errore salvataggio persona: " + ex.getMessage(), "Errore", JOptionPane.ERROR_MESSAGE);
        }
    }

    // --- SCHERMATA 2: PIANI RIABILITATIVI ---
    private JPanel createPlanPanel() {
        JPanel container = new JPanel(new BorderLayout(16, 16));
        container.setBackground(COLOR_MAIN_BG);
        container.setBorder(new EmptyBorder(20, 20, 20, 20));

        container.add(createScreenHeader("🔄 Ciclo di Vita Piani Riabilitativi (State Pattern)",
                "Crea piani in bozza e simula le transizioni di stato con notifiche Observer"), BorderLayout.NORTH);

        JPanel content = new JPanel(new BorderLayout(16, 16));
        content.setBackground(COLOR_MAIN_BG);

        // Form Creazione Top
        JPanel createCard = new JPanel(new BorderLayout(12, 12));
        createCard.setBackground(COLOR_CARD_BG);
        createCard.setBorder(createCardBorder("Creazione Nuovo Piano Riabilitativo (DraftState)"));

        JPanel createFields = new JPanel(new GridLayout(2, 4, 10, 10));
        createFields.setBackground(COLOR_CARD_BG);

        cbPatientSelect = new JComboBox<>();
        cbDoctorSelect = new JComboBox<>();
        txtProtocol = createStyledTextField("Riabilitazione Funzionale Post-Op LCA Ginocchio");
        txtPlannedSessions = createStyledTextField("3");

        styleComboBox(cbPatientSelect);
        styleComboBox(cbDoctorSelect);

        createFields.add(createDarkLabel("Paziente:")); createFields.add(cbPatientSelect);
        createFields.add(createDarkLabel("Medico Prescrittore:")); createFields.add(cbDoctorSelect);
        createFields.add(createDarkLabel("Protocollo Clinico:")); createFields.add(txtProtocol);
        createFields.add(createDarkLabel("Sedute Pianificate:")); createFields.add(txtPlannedSessions);

        JButton btnCreatePlan = createPrimaryButton("✨  Crea Piano in Bozza");
        btnCreatePlan.addActionListener(e -> createPlanAction());

        createCard.add(createFields, BorderLayout.CENTER);
        createCard.add(btnCreatePlan, BorderLayout.SOUTH);

        // Controls Middle
        JPanel middleGrid = new JPanel(new GridLayout(1, 2, 16, 16));
        middleGrid.setBackground(COLOR_MAIN_BG);

        // Transizioni State Pattern
        JPanel stateCard = new JPanel(new GridLayout(7, 1, 6, 6));
        stateCard.setBackground(COLOR_CARD_BG);
        stateCard.setBorder(createCardBorder("Transizioni State Pattern (Ciclo di Vita)"));

        cbPlanSelect = new JComboBox<>();
        styleComboBox(cbPlanSelect);
        cbPlanSelect.addActionListener(e -> updatePlanDetailsView());

        JButton btnPrescribe = createOutlineButton("1. Prescrivi Piano (PrescribedState)", new Color(2, 132, 199));
        JButton btnStart = createOutlineButton("2. Avvia Trattamento (InProgressState)", new Color(13, 148, 136));
        JButton btnSuspend = createOutlineButton("3. Sospendi Piano (SuspendedState)", new Color(217, 119, 6));
        JButton btnComplete = createOutlineButton("4. Completa Sedute (CompletedState)", new Color(124, 58, 237));
        JButton btnClearance = createOutlineButton("5. Rilascia Idoneità (ClearedState)", new Color(5, 150, 105));

        btnPrescribe.addActionListener(e -> handleStateTransition("prescribe"));
        btnStart.addActionListener(e -> handleStateTransition("start"));
        btnSuspend.addActionListener(e -> handleStateTransition("suspend"));
        btnComplete.addActionListener(e -> handleStateTransition("complete"));
        btnClearance.addActionListener(e -> handleStateTransition("clearance"));

        stateCard.add(cbPlanSelect);
        stateCard.add(btnPrescribe);
        stateCard.add(btnStart);
        stateCard.add(btnSuspend);
        stateCard.add(btnComplete);
        stateCard.add(btnClearance);

        txtPlanDetails = createDarkTextArea();
        JPanel detailsCard = new JPanel(new BorderLayout());
        detailsCard.setBackground(COLOR_CARD_BG);
        detailsCard.setBorder(createCardBorder("Dettagli Piano Selezionato"));
        detailsCard.add(new JScrollPane(txtPlanDetails), BorderLayout.CENTER);

        middleGrid.add(stateCard);
        middleGrid.add(detailsCard);

        txtObserverLog = createDarkTextArea();
        JPanel observerCard = new JPanel(new BorderLayout());
        observerCard.setBackground(COLOR_CARD_BG);
        observerCard.setBorder(createCardBorder("Observer Log (DoctorAlertNotifier & PatientReminderNotifier)"));
        observerCard.setPreferredSize(new Dimension(0, 140));
        observerCard.add(new JScrollPane(txtObserverLog), BorderLayout.CENTER);

        content.add(createCard, BorderLayout.NORTH);
        content.add(middleGrid, BorderLayout.CENTER);
        content.add(observerCard, BorderLayout.SOUTH);

        container.add(content, BorderLayout.CENTER);
        return container;
    }

    private void createPlanAction() {
        try {
            String patientSel = (String) cbPatientSelect.getSelectedItem();
            String doctorSel = (String) cbDoctorSelect.getSelectedItem();
            if (patientSel == null || doctorSel == null) return;

            String pTax = patientSel.split(" - ")[0];
            String dTax = doctorSel.split(" - ")[0];
            int planned = Integer.parseInt(txtPlannedSessions.getText());

            RehabPlan plan = planService.createPlan(pTax, dTax, txtProtocol.getText(), LocalDate.now(), planned);
            plan.attach(doctorNotifier);
            plan.attach(patientNotifier);

            txtPlanDetails.setText("PIANO CREATO CON SUCCESSO:ID: " + plan.getId() + "Stato: " + plan.getStateName());
            refreshDropdowns();
            refreshDatabaseTables();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Errore creazione piano: " + ex.getMessage(), "Errore", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void handleStateTransition(String transition) {
        String selected = (String) cbPlanSelect.getSelectedItem();
        if (selected == null) return;
        String planId = selected.split(" - ")[0];

        try {
            RehabPlan plan = planService.getPlanOrThrow(planId);
            plan.attach(doctorNotifier);
            plan.attach(patientNotifier);

            switch (transition) {
                case "prescribe" -> planService.prescribePlan(planId);
                case "start" -> planService.startTreatment(planId);
                case "suspend" -> planService.suspendPlan(planId, "Valutazione clinica intermedia");
                case "complete" -> planService.completeTreatment(planId);
                case "clearance" -> planService.issueClearance(planId);
            }

            updatePlanDetailsView();
            txtObserverLog.append("EVENTO OBSERVER: [" + plan.getId() + "] -> Stato: " + plan.getStateName() + "");
            refreshDatabaseTables();
        } catch (IllegalStateException ex) {
            JOptionPane.showMessageDialog(this, "TRANSIZIONE NON PERMESSA (State Pattern):" + ex.getMessage(), "Stato Non Valido", JOptionPane.WARNING_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Errore: " + ex.getMessage(), "Errore", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updatePlanDetailsView() {
        String selected = (String) cbPlanSelect.getSelectedItem();
        if (selected == null) return;
        String planId = selected.split(" - ")[0];
        planDb.findById(planId).ifPresent(p -> {
            txtPlanDetails.setText("=== DETTAGLI REHAB PLAN ===ID Piano: " + p.getId() +
                    "Paziente: " + p.getPatientTaxCode() +
                    "Medico: " + p.getDoctorTaxCode() +
                    "Stato Corrente: " + p.getStateName() +
                    "Progressi Sedute: " + p.getExecutedSessions() + " / " + p.getPlannedSessions() + " completate" +
                    "Protocollo: " + p.getProtocol());
        });
    }

    // --- SCHERMATA 3: CALENDARIO SEDUTE & VALUTAZIONE CLINICA ---
    private JPanel createSessionPanel() {
        JPanel container = new JPanel(new BorderLayout(16, 16));
        container.setBackground(COLOR_MAIN_BG);
        container.setBorder(new EmptyBorder(20, 20, 20, 20));

        container.add(createScreenHeader("📅 Calendario Sedute e Valutazione Clinica ROM/Dolore",
                "Registra gli esiti clinici delle sedute svolte dai fisioterapisti nelle stanze di riabilitazione"), BorderLayout.NORTH);

        JPanel mainGrid = new JPanel(new GridLayout(1, 2, 16, 16));
        mainGrid.setBackground(COLOR_MAIN_BG);

        // Form Booking Left
        JPanel formCard = new JPanel(new BorderLayout(12, 12));
        formCard.setBackground(COLOR_CARD_BG);
        formCard.setBorder(createCardBorder("Prenotazione & Registrazione Seduta"));

        JPanel formFields = new JPanel(new GridLayout(7, 2, 8, 8));
        formFields.setBackground(COLOR_CARD_BG);

        cbBookingPlanSelect = new JComboBox<>();
        cbPhysioSelect = new JComboBox<>();
        cbRoomSelect = new JComboBox<>(new String[]{"ROOM-101 (Palestra)", "ROOM-102 (Vasca Idrokinesi)", "ROOM-201 (Ambulatorio Terapia)"});

        styleComboBox(cbBookingPlanSelect);
        styleComboBox(cbPhysioSelect);
        styleComboBox(cbRoomSelect);

        txtDateTime = createStyledTextField(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
        txtRom = createStyledTextField("115.0");

        sliderPain = new JSlider(0, 10, 3);
        sliderPain.setBackground(COLOR_CARD_BG);
        sliderPain.setMajorTickSpacing(2);
        sliderPain.setPaintTicks(true);
        sliderPain.setPaintLabels(true);

        lblPainValue = new JLabel("3 / 10");
        lblPainValue.setFont(new Font("Segoe UI", Font.BOLD, 12));
        lblPainValue.setForeground(COLOR_TEXT_DARK);
        sliderPain.addChangeListener(e -> lblPainValue.setText(sliderPain.getValue() + " / 10"));

        JPanel painBox = new JPanel(new BorderLayout(4, 4));
        painBox.setBackground(COLOR_CARD_BG);
        painBox.add(sliderPain, BorderLayout.CENTER);
        painBox.add(lblPainValue, BorderLayout.EAST);

        txtSessionNotes = createDarkTextArea();
        txtSessionNotes.setText("Paziente riferisce sensibile miglioramento dell'estensione del ginocchio.");

        formFields.add(createDarkLabel("Piano Riabilitativo:")); formFields.add(cbBookingPlanSelect);
        formFields.add(createDarkLabel("Data/Ora Seduta:")); formFields.add(txtDateTime);
        formFields.add(createDarkLabel("Fisioterapista:")); formFields.add(cbPhysioSelect);
        formFields.add(createDarkLabel("Stanza Clinica:")); formFields.add(cbRoomSelect);
        formFields.add(createDarkLabel("ROM Misurato (°):")); formFields.add(txtRom);
        formFields.add(createDarkLabel("Dolore (0-10):")); formFields.add(painBox);
        formFields.add(createDarkLabel("Note Cliniche:")); formFields.add(new JScrollPane(txtSessionNotes));

        JButton btnBook = createPrimaryButton("📝  Prenota e Registra Esito Clinico");
        btnBook.addActionListener(e -> bookAndRecordSessionAction());

        formCard.add(formFields, BorderLayout.CENTER);
        formCard.add(btnBook, BorderLayout.SOUTH);

        // Outcome Log Right
        JPanel outcomeCard = new JPanel(new BorderLayout(8, 8));
        outcomeCard.setBackground(COLOR_CARD_BG);
        outcomeCard.setBorder(createCardBorder("Registro Esiti Clinici & Stima Recupero %"));

        txtOutcomeLog = createDarkTextArea();
        outcomeCard.add(new JScrollPane(txtOutcomeLog), BorderLayout.CENTER);

        mainGrid.add(formCard);
        mainGrid.add(outcomeCard);

        container.add(mainGrid, BorderLayout.CENTER);
        return container;
    }

    private void bookAndRecordSessionAction() {
        try {
            String planSel = (String) cbBookingPlanSelect.getSelectedItem();
            String physioSel = (String) cbPhysioSelect.getSelectedItem();
            String roomSel = (String) cbRoomSelect.getSelectedItem();
            if (planSel == null || physioSel == null || roomSel == null) return;

            String planId = planSel.split(" - ")[0];
            String physioTax = physioSel.split(" - ")[0];
            String roomId = roomSel.split(" ")[0];

            String dtStr = txtDateTime.getText().trim();
            LocalDateTime sessionDateTime;
            try {
                sessionDateTime = LocalDateTime.parse(dtStr.replace(" ", "T"));
            } catch (Exception ex) {
                sessionDateTime = LocalDateTime.now();
            }

            Session session = bookingService.bookSession(planId, sessionDateTime, physioTax, roomId);
            RehabPlan plan = planService.getPlanOrThrow(planId);
            plan.attach(doctorNotifier);

            double rom = Double.parseDouble(txtRom.getText());
            int pain = sliderPain.getValue();

            evaluationService.recordAndEvaluateSessionOutcome(plan, session, rom, pain, txtSessionNotes.getText());
            double recovery = evaluationService.calculateRecoveryPercentage(plan, 120.0);

            txtOutcomeLog.append("SEDUTA REGISTRATA [" + sessionDateTime + "]: ID=" + session.getId() +
                    " | ROM=" + rom + "° | Dolore=" + pain + "/10 | Stima Recupero=" + recovery + "%");

            refreshDropdowns();
            refreshDatabaseTables();
        } catch (IllegalStateException ex) {
            JOptionPane.showMessageDialog(this, "CONFLITTO PRENOTAZIONE O ERRORE STATO:" + ex.getMessage(), "Attenzione", JOptionPane.WARNING_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Errore durante la registrazione: " + ex.getMessage(), "Errore", JOptionPane.ERROR_MESSAGE);
        }
    }

    // --- SCHERMATA 4: VIEWER DATABASE SQLITE ---
    private JPanel createDatabaseViewerPanel() {
        JPanel container = new JPanel(new BorderLayout(16, 16));
        container.setBackground(COLOR_MAIN_BG);
        container.setBorder(new EmptyBorder(20, 20, 20, 20));

        JPanel headerBar = new JPanel(new BorderLayout());
        headerBar.setBackground(COLOR_MAIN_BG);

        headerBar.add(createScreenHeader("🗄️ Viewer Tabelle Database SQLite",
                "Visualizzazione ed ispezione diretta delle tabelle memorizzate in medicalhub.db"), BorderLayout.WEST);

        JButton btnRefresh = createOutlineButton("🔄  Ricarica Dati SQLite", COLOR_PRIMARY);
        btnRefresh.addActionListener(e -> refreshDatabaseTables());
        headerBar.add(btnRefresh, BorderLayout.EAST);

        JTabbedPane dbSubTabs = new JTabbedPane();
        dbSubTabs.setFont(FONT_PLAIN);

        modelPersons = new DefaultTableModel(new String[]{"Codice Fiscale", "Nome", "Cognome", "Data Nascita"}, 0);
        tablePersons = new JTable(modelPersons);
        styleModernTable(tablePersons);
        dbSubTabs.addTab("Tabella: persons", new JScrollPane(tablePersons));

        modelPlans = new DefaultTableModel(new String[]{"ID Piano", "Paziente (Tax)", "Medico (Tax)", "Protocollo", "Data Inizio", "Sedute (Eseguite/Pianificate)", "Stato"}, 0);
        tablePlans = new JTable(modelPlans);
        styleModernTable(tablePlans);
        dbSubTabs.addTab("Tabella: rehab_plans", new JScrollPane(tablePlans));

        modelSessions = new DefaultTableModel(new String[]{"ID Seduta", "ID Piano", "Data/Ora", "Fisioterapista", "Stanza", "ROM (°)", "Dolore (/10)", "Completata", "Note"}, 0);
        tableSessions = new JTable(modelSessions);
        styleModernTable(tableSessions);
        dbSubTabs.addTab("Tabella: sessions", new JScrollPane(tableSessions));

        container.add(headerBar, BorderLayout.NORTH);
        container.add(dbSubTabs, BorderLayout.CENTER);

        return container;
    }

    private void refreshDatabaseTables() {
        try (Connection conn = DatabaseConnection.getInstance().getConnection();
             Statement stmt = conn.createStatement()) {

            modelPersons.setRowCount(0);
            ResultSet rsP = stmt.executeQuery("SELECT * FROM persons");
            while (rsP.next()) {
                modelPersons.addRow(new Object[]{
                        rsP.getString("tax_code"),
                        rsP.getString("first_name"),
                        rsP.getString("last_name"),
                        rsP.getString("birth_date")
                });
            }

            modelPlans.setRowCount(0);
            ResultSet rsPl = stmt.executeQuery("SELECT * FROM rehab_plans");
            while (rsPl.next()) {
                modelPlans.addRow(new Object[]{
                        rsPl.getString("id"),
                        rsPl.getString("patient_tax_code"),
                        rsPl.getString("doctor_tax_code"),
                        rsPl.getString("protocol"),
                        rsPl.getString("start_date"),
                        rsPl.getInt("executed_sessions") + " / " + rsPl.getInt("planned_sessions"),
                        rsPl.getString("state_name")
                });
            }

            modelSessions.setRowCount(0);
            ResultSet rsS = stmt.executeQuery("SELECT * FROM sessions");
            while (rsS.next()) {
                modelSessions.addRow(new Object[]{
                        rsS.getString("id"),
                        rsS.getString("plan_id"),
                        rsS.getString("date_time"),
                        rsS.getString("physio_tax_code"),
                        rsS.getString("room_id"),
                        rsS.getDouble("rom_degrees") + "°",
                        rsS.getInt("pain_level") + "/10",
                        rsS.getBoolean("completed") ? "SÌ" : "NO",
                        rsS.getString("clinical_notes")
                });
            }

        } catch (Exception ex) {
            System.err.println("Errore caricamento tabelle SQLite: " + ex.getMessage());
        }
    }

    // --- HELPER DI STILE E UI ---
    private JPanel createScreenHeader(String title, String subtitle) {
        JPanel p = new JPanel();
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        p.setBackground(COLOR_MAIN_BG);

        JLabel t = new JLabel(title);
        t.setFont(new Font("Segoe UI", Font.BOLD, 18));
        t.setForeground(COLOR_TEXT_DARK);

        JLabel s = new JLabel(subtitle);
        s.setFont(FONT_PLAIN);
        s.setForeground(COLOR_TEXT_MUTED);

        p.add(t);
        p.add(Box.createRigidArea(new Dimension(0, 4)));
        p.add(s);
        return p;
    }

    private JTextField createStyledTextField(String defaultVal) {
        JTextField tf = new JTextField(defaultVal);
        tf.setFont(FONT_PLAIN);
        tf.setForeground(COLOR_TEXT_DARK);
        tf.setBackground(COLOR_CARD_BG);
        tf.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(COLOR_BORDER, 1),
                new EmptyBorder(6, 10, 6, 10)
        ));
        tf.putClientProperty("JComponent.roundRect", true);
        return tf;
    }

    private JTextArea createDarkTextArea() {
        JTextArea ta = new JTextArea();
        ta.setFont(FONT_MONO);
        ta.setForeground(COLOR_TEXT_DARK);
        ta.setBackground(COLOR_CARD_BG);
        ta.setLineWrap(true);
        ta.setWrapStyleWord(true);
        return ta;
    }

    private JLabel createDarkLabel(String text) {
        JLabel l = new JLabel(text);
        l.setFont(FONT_PLAIN);
        l.setForeground(COLOR_TEXT_DARK);
        return l;
    }

    private JCheckBox createStyledCheckBox(String text, boolean sel) {
        JCheckBox cb = new JCheckBox(text, sel);
        cb.setFont(new Font("Segoe UI", Font.BOLD, 12));
        cb.setForeground(COLOR_TEXT_DARK);
        cb.setBackground(COLOR_CARD_BG);
        return cb;
    }

    private void styleComboBox(JComboBox<?> cb) {
        cb.setFont(FONT_PLAIN);
        cb.setForeground(COLOR_TEXT_DARK);
        cb.setBackground(COLOR_CARD_BG);
        cb.putClientProperty("JComponent.roundRect", true);
    }

    private JButton createPrimaryButton(String text) {
        JButton btn = new JButton(text);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btn.setForeground(Color.WHITE);
        btn.setBackground(COLOR_PRIMARY);
        btn.setFocusPainted(false);
        btn.setBorder(new EmptyBorder(10, 16, 10, 16));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.putClientProperty("JComponent.roundRect", true);
        return btn;
    }

    private JButton createOutlineButton(String text, Color color) {
        JButton btn = new JButton(text);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btn.setForeground(color);
        btn.setBackground(COLOR_CARD_BG);
        btn.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(color, 1),
                new EmptyBorder(8, 14, 8, 14)
        ));
        btn.setFocusPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.putClientProperty("JComponent.roundRect", true);
        return btn;
    }

    private CompoundBorder createCardBorder(String title) {
        TitledBorder tb = BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(COLOR_BORDER, 1),
                title
        );
        tb.setTitleFont(FONT_TITLE);
        tb.setTitleColor(COLOR_PRIMARY);
        return BorderFactory.createCompoundBorder(tb, new EmptyBorder(10, 10, 10, 10));
    }

    private void styleModernTable(JTable table) {
        table.setFont(FONT_PLAIN);
        table.setForeground(COLOR_TEXT_DARK);
        table.setBackground(COLOR_CARD_BG);
        table.setRowHeight(36);
        table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        table.getTableHeader().setBackground(new Color(241, 245, 249));
        table.getTableHeader().setForeground(COLOR_TEXT_DARK);
        table.setGridColor(COLOR_BORDER);
        table.setSelectionBackground(new Color(224, 242, 254));
        table.setSelectionForeground(COLOR_TEXT_DARK);
    }

    private void refreshDropdowns() {
        cbPatientSelect.removeAllItems();
        cbDoctorSelect.removeAllItems();
        cbPhysioSelect.removeAllItems();
        cbPlanSelect.removeAllItems();
        cbBookingPlanSelect.removeAllItems();

        List<Person> persons = personDb.findAll();
        for (Person p : persons) {
            if (p.hasRole(PatientRole.class)) {
                cbPatientSelect.addItem(p.getTaxCode() + " - " + p.getFullName());
            }
            if (p.hasRole(DoctorRole.class)) {
                cbDoctorSelect.addItem(p.getTaxCode() + " - " + p.getFullName());
            }
            if (p.hasRole(PhysiotherapistRole.class)) {
                cbPhysioSelect.addItem(p.getTaxCode() + " - " + p.getFullName());
            }
        }

        List<RehabPlan> plans = planDb.findAll();
        for (RehabPlan pl : plans) {
            cbPlanSelect.addItem(pl.getId() + " - " + pl.getProtocol() + " [" + pl.getStateName() + "]");
            cbBookingPlanSelect.addItem(pl.getId() + " - " + pl.getProtocol());
        }
    }
}
