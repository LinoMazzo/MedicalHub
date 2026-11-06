# MedicalHub - Progetto Universitario Multi-Modulo Maven

Sistema Software per la Gestione di Strutture Riabilitative Mediche.
Sviluppato in Java 17/21 con architettura Maven Multi-Modulo, Design Pattern (Player-Role, State, Observer), SQLite JDBC e GUI Java Swing.

## 📁 Struttura Moduli Maven
- **`med-core`**: Modello di Dominio puro, Design Pattern (Player-Role Lethbridge 6.4, State Pattern per il ciclo di vita del RehabPlan, Observer Pattern per notifica medici e pazienti), interfacce di Repository e Servizi applicativi (`RehabPlanService`, `BookingService`, `EvaluationService`).
- **`med-database`**: Gestione della connessione Singleton SQLite JDBC (`jdbc:sqlite:medicalhub.db`), auto-inizializzazione DDL delle tabelle al primo avvio e implementazioni concrete DAO (`SQLitePersonDatabase`, `SQLiteRehabPlanDatabase`, `SQLiteSessionDatabase`).
- **`med-gui`**: Interfaccia Desktop Swing (`JFrame`, `JTabbedPane`) organizzata in 3 schede per testare Anagrafica con Ruoli, Avanzamento Stati e Prenotazione/Valutazione Sedute.

## 🚀 Istruzioni di Compilazione ed Esecuzione

### 1. Requisiti
- JDK 17 o JDK 21 installato.
- Apache Maven 3.8+ oppure Eclipse / IntelliJ IDEA.

### 2. Compilazione tramite Terminale / Maven CLI
Dalla radice del progetto (`MedicalHub/`):

```bash
# Pulizia e compilazione di tutti e tre i moduli
mvn clean compile

# Esecuzione dei Test Unitari
mvn test

# Pacchettizzazione dei file JAR
mvn clean package
```

### 3. Esecuzione della GUI Swing
Per avviare l'applicazione desktop Swing:

```bash
# Tramite Maven Exec Plugin (nel modulo med-gui)
cd med-gui
mvn exec:java -Dexec.mainClass="com.medicalhub.gui.Main"

# Oppure tramite il JAR pacchettizzato
java -jar med-gui/target/med-gui-1.0.0.jar
```

### 4. Importazione in Eclipse IDE
1. Apri Eclipse.
2. Seleziona **File -> Import... -> Maven -> Existing Maven Projects**.
3. Seleziona la cartella radice `MedicalHub` dove risiede il `pom.xml` radice.
4. Assicurati che tutti e tre i moduli (`med-core`, `med-database`, `med-gui`) vengano spuntati e clicca **Finish**.
5. Fai clic destro su `med-gui/src/main/java/com/medicalhub/gui/Main.java` -> **Run As -> Java Application**.

---
*Progetto generato con codice sorgente completo al 100% senza alcuna omissione.*
