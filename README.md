# MedicalHub

> Piattaforma per la Gestione di Percorsi Riabilitativi, Fisioterapia e Medicina dello Sport

Progetto per il corso di **Ingegneria del Software**  
**Anno Accademico:** 2026/2027  
**Docenti:** Prof. Angelo Gargantini, Dott.ssa Silvia Bonfanti  
**Università degli Studi di Bergamo**

---

## Il Team di Sviluppo

| Ruolo | Nome e Cognome | Matricola | Account GitHub |
| :--- | :--- | :--- | :--- |
| **Lead Analyst & QA Manager** | Franchini Mattia | 1099581 | @Franchini-Mattia(https://github.com/Franchini-Mattia) |
| **Software Architect & Modeler** | Mazzoleni Lino | 1098962 | [@LinoMazzo](https://github.com/LinoMazzo) |
| **Lead Developer & Test Engineer** | Ghislanzoni Fabio | 1099423 | [@username3](https://github.com) |
| **Lead Developer & Test Engineer** | Ivasco Riccardo | 1101140 | [@riccardoivasco](https://github.com/riccardoivasco) |

---

## Indice della Documentazione

La documentazione del progetto è organizzata secondo le linee guida ufficiali del corso nei seguenti 6 documenti consultabili nella cartella `documenti/`:

* [**Documento 1: Project Plan**](./documenti/01_Project_Plan.md)  
  *Contenuto:* Software Engineering Management (i 14 punti del modello di gestione, pianificazione temporale, rischi, staffing, WBS).

* [**Documento 2: Gestione del Progetto**](./documenti/02_Project_Management.md)  
  *Contenuto:* Software Life Cycle, Configuration Management (Git Flow, branch, review), People Management e Team Organization.

* [**Documento 3: Requisiti del Sistema**](./documenti/03_Requirements.md)  
  *Contenuto:* Requirements Engineering (Standard IEEE 830, casi d'uso, requisiti funzionali e non funzionali) e Software Quality.

* [**Documento 4: Architettura e Design**](./documenti/04_Design_Architecture.md)  
  *Contenuto:* Modellazione UML (StarUML), Viste architetturali, Package Diagram, Component Diagram e Design Pattern applicati (*State, Strategy, Observer*).

* [**Documento 5: Testing e Validazione**](./documenti/05_Testing.md)  
  *Contenuto:* Strategia di collaudo, Casi di test di unità con JUnit 5, Misure e report di Coverage (copertura del codice).

* [**Documento 6: Manutenzione e Qualità del Codice**](./documenti/06_Maintenance.md)  
  *Contenuto:* Attività di refactoring, analisi statica del codice (metriche McCabe, SonarLint/PMD) e debito tecnico.

---

## Struttura del Repository

La struttura del repository rispetta rigorosamente la separazione tra codice e documentazione richiesta:

* **README.md**: Presentazione del progetto e indice generale
* **.gitignore**: Configurazione dei file ignorati dal versionamento
* **documenti/**: Cartella contenente la documentazione tecnica e i modelli
  * **01_Project_Plan.md**
  * **02_Project_Management.md**
  * **03_Requirements.md**
  * **04_Design_Architecture.md**
  * **05_Testing.md**
  * **06_Maintenance.md**
  * **models/**: File di progetto StarUML (.mdj) ed esportazioni grafiche
* **codice/**: Cartella contenente il codice sorgente
  * **medicalhub/**: Progetto Java gestito con Maven ed Eclipse
    * **pom.xml**: Gestione delle dipendenze (JUnit 5, Log4j, ecc.)
    * **.project**: File di configurazione Eclipse
    * **.classpath**: File di configurazione del classpath Eclipse
    * **src/main/java/**: Codice sorgente applicativo
    * **src/test/java/**: Casi di test di unità JUnit

---

## Tecnologie e Strumenti Utilizzati

* **Linguaggio di Programmazione:** Java 17 o 21
* **Build Automation e Dipendenze:** Apache Maven
* **IDE Ufficiale:** Eclipse IDE
* **Modellazione UML:** StarUML v5
* **Unit Testing:** JUnit 5 (Jupiter)
* **Logging:** Apache Log4j 2
* **Version Control e Collaborazione:** Git e GitHub (Feature Branching, Pull Requests, Project Board)

---

## Guida alla Compilazione ed Esecuzione

### Prerequisiti
* **Java Development Kit (JDK):** Versione 17 o superiore
* **Apache Maven:** Versione 3.8+ (oppure integrato in Eclipse)
* **Eclipse IDE:** Versione recente con supporto Java/Maven

### Importazione in Eclipse
1. Aprire Eclipse.
2. Cliccare su **File** e poi su **Import...**.
3. Selezionare **Maven** e successivamente **Existing Maven Projects**.
4. Alla voce **Root Directory** selezionare la cartella `codice/medicalhub`.
5. Cliccare su **Finish**.

### Esecuzione dei Test
Dalla cartella `codice/medicalhub`, è possibile eseguire tutti i test unitari tramite Maven con il comando:

```bash
mvn clean test