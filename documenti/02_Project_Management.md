# DOCUMENTO 2: GESTIONE DEL PROGETTO

**Progetto:** MedicalHub  
**Corso:** Ingegneria del Software (AA 2026/27)  
**Team:** Franchini Mattia, Ghislanzoni Fabio, Mazzoleni Lino, Ivasco Riccardo  

### Storico delle Versioni
| Versione | Data | Autori | Descrizione delle modifiche |
| :--- | :--- | :--- | :--- |
| **1.0.0** | 13/10/2026 | Ghislanzoni Fabio | Versione iniziale del Documento di Gestione del Progetto |
| **1.1.0** | 10/11/2026 | Ghislanzoni Fabio | Major updates |

---

## 2.1 Software Life Cycle

In conformità a quanto stabilito nel *Project Plan*, abbiamo adottato un modello di processo di tipo **Agile iterativo e incrementale**, fortemente ispirato alle cerimonie e alle pratiche del framework **Scrum**. Questo approccio ha consentito di gestire l'affinamento progressivo dei requisiti clinici, mitigare tempestivamente i rischi tecnici e disporre di incrementi software stabili ed eseguibili al termine di ciascuna iterazione.

### Approccio MDA (Model-Driven Architecture)
In linea con le direttive metodologiche del corso, lo sviluppo del software non è avvenuto scrivendo l'intero codice da zero, bensì applicando parzialmente i principi della **Model-Driven Architecture (MDA)**:
1. **Modello Indipendente dalla Piattaforma (PIM):** Durante le prime fasi di analisi, il team ha formalizzato il dominio applicativo realizzando un Class Diagram concettuale dettagliato in **StarUML v5**.
2. **Generazione Automatica del Codice (PIM -> PSM):** Mediante l'estensione **Javazzar** di StarUML, abbiamo autogenerato lo scheletro iniziale delle classi Java (struttura dei package, attributi privati, costruttori, firme dei metodi, interfacce e relazioni di ereditarietà/implementazione...)
3. **Raffinamento e Implementazione Manuale:** Il codice generato è stato importato nell'ambiente **Eclipse IDE**, dove abbiamo implementato manualmente la logica di business interna dei metodi, le regole di validazione clinica, la gestione delle eccezioni e le query di persistenza SQL.

### Formalizzazione del Processo: Gestione di una Change Request
In accordo con le linee guida (che suggeriscono di formalizzare con diagrammi UML una parte del ciclo di sviluppo), il flusso operativo adottato dal team per la presa in carico di una nuova funzionalità o per la gestione di una modifica (Change Request) è così strutturato:

    [Segnalazione Modifica/Task] ──► [Apertura Issue su GitHub]
                                               │
                                               ▼
    [Assegnazione a un Componente]     ◄── 
                │
                ▼
    [Creazione Feature Branch (da develop)]
                │
                ▼
    [Sviluppo Codice / Documenti + Test JUnit]
                │
                ▼
    [Apertura Pull Request verso develop]
                │
                ▼
    [Peer Code Review da parte di un compagno (o più)]
                │
           ┌────┴──────────────┐
      (Approvato)         (Modifiche richieste)
           │                   │
           ▼                   ▼
    [Merge in develop]    [Refactoring su Feature Branch]
           │
           ▼
    [Chiusura automatica Issue]

### Organizzazione in Sprint (Timebox)
Il cronoprogramma effettivo dello sviluppo è stato ripartito in **4 Sprint** a timebox rigido di 2 settimane ciascuno:

* **Sprint 1 (01/10/2026 – 16/10/2026):**
  * *Obiettivi:* Analisi preliminare del problema, stesura del *Project Plan* (Doc 1), definizione dei ruoli, impostazione del repository GitHub e prima elicitazione dei requisiti.
  * *Output:* Project Plan v1.0.0 sottomesso per approvazione formale al docente tramite GitHub Issue.
* **Sprint 2 (17/10/2026 – 31/10/2026):**
  * *Obiettivi:* Redazione della specifica formale dei requisiti (Doc 3 IEEE 830 - prima versione) e Use Case Diagram; modellazione del Class Diagram in StarUML v5; generazione dello scheletro del codice tramite Javazzar.
  * *Output:* Modello StarUML (`MedicalHub.mdj`), prime classi Java di dominio compilabili.
* **Sprint 3 (01/11/2026 – 15/11/2026):**
  * *Obiettivi:* Ristrutturazione architetturale nei 3 moduli Maven (`MED_core`, `MED_database`, `MED_gui`); implementazione della logica dei servizi; integrazione del database embedded SQLite (`DatabaseConnection` Singleton e DAO).
  * *Output:* Build Maven verde (`mvn clean test`), file di database locale `medicalhub.db` generato a runtime, prime classi GUI.
* **Sprint 4 (16/11/2026 – 30/11/2026):**
  * *Obiettivi:* Completamento dell'interfaccia utente grafica; stesura della suite di test unitari JUnit 5 per il core e la persistenza;  analisi statica del codice (calcolo metriche); refactoring degli antipattern; consolidamento dei 6 documenti finali e freeze del codice 5 giorni prima della presentazione orale.
  * *Output:* Release stabile su branch `main`, slide della presentazione e repository collaudata per la demo live.

### Scostamenti dal Piano Originale (Lessons Learned)
Durante l'avanzamento dei lavori si sono verificati alcuni scostamenti rispetto alla pianificazione iniziale, gestiti tempestivamente:
1. **Transizione da Monolite a Multi-Modulo Maven:** Inizialmente era stato previsto un unico progetto Java suddiviso in package. Per garantire un disaccoppiamento fisico e impedire dipendenze cicliche a livello di compilazione, abbiamo scelto di riconfigurare la base di codice in 3 progetti Maven separati (`MED_core`, `MED_database`, `MED_gui`), come consigliato dal docente a lezione.
2. **Scelta del DBMS Embedded (da H2 a SQLite):** Sebbene la prima bozza ipotizzasse l'uso di H2, abbiamo optato in corso d'opera per **SQLite** tramite driver `sqlite-jdbc`. Questa soluzione ha semplificato la gestione locale della persistenza tramite un unico file portatile (`medicalhub.db`) con percorsi relativi, azzerando i rischi di path assoluti non portabili.
3. **Risoluzione Conflitti sul Branching:** Un tentativo di sincronizzazione concorrente sul file del Project Plan ha generato un merge conflict su Git. L'inconveniente è stato risolto con una sessione straordinaria di allineamento;

---

## 2.2 Configuration Management

La gestione delle configurazioni, del versionamento del software e del controllo concorrente è stata interamente affidata all'ecosistema **Git** e alla piattaforma cloud **GitHub**, garantendo tracciabilità, integrità della storia del codice e cooperazione strutturata.

### Organizzazione del Workspace
La struttura del repository rispetta rigorosamente le direttive d'esame, mantenendo una netta separazione fisica tra documentazione e artefatti software:
* `/documenti`: Contiene i 6 documenti Markdown della relazione tecnica finale e la cartella `/models` contenente i diagrammi di StarUML.
* `/codice`: Contiene esclusivamente i progetti software sviluppati per ambiente **Eclipse IDE**. Al suo interno si trovano le 3 cartelle dei moduli Maven:
  * `/codice/MED_core`.
  * `/codice/MED_database`.
  * `/codice/MED_gui`.

### Gestione del `.gitignore` e Metadati Eclipse

* **File Ignorati:** È stato configurato un file `.gitignore` nella radice del repository per escludere file binari di compilazione (`*.class`), directory di build Maven (`/target`), file di log e il file locale del database (`*.db`, `*.db-journal`), evitando conflitti su file binari tra gli sviluppatori.

### Versioning e Feature-Branch Workflow
Il team ha bandito qualsiasi modifica o commit diretto sui rami principali, adottando una variante rigorosa del **Feature-Branch Workflow**. I comandi di terminale quotidianamente utilizzati comprendono: `git fetch`, `git checkout -b feature/...`, `git add`, `git commit -m "..."`, `git push origin ...` e `git pull` (è stato utilizzato talvolta anche GitHub Desktop, senza ricorrere ai comandi da cli ).

Le regole formali del flusso di lavoro sono:
1. **Branch `main`:** È il ramo che ospita esclusivamente le release stabili, documentate e collaudate (es. tag `v1.0.0` del Project Plan, release finale per l'esame).
2. **Branch `develop`:** Ramo principale di sviluppo e integrazione continua. Tutti i branch di funzionalità partono da `develop` e riconfluiscono in esso.
3. **Branch `feature/<nome-task>`:** Rami temporanei creati per ciascun task assegnato (es. `feature/setup-3-tier-architecture`, `feature/sqlite-persistence`, `feature/doc3-requirements`).
4. **Pull Request (PR) e Code Review Obbligatoria:** Al completamento di una funzionalità, lo sviluppatore apre una Pull Request da `feature/*` verso `develop`. Il merge è autorizzato solo previo superamento di una revisione tra pari (*Peer Review*) con esito positivo (**Approve**) da parte di almeno un compagno di gruppo, verificando l'assenza di warning e la presenza dei test.
5. **Cancellazione dei branch completati:** Subito dopo il merge su GitHub, il branch temporaneo viene eliminato dal repository remoto per prevenire il disordine, mantenendo pulito lo storico delle PR archiviate.

### Task Tracking e Metriche del Repository
L'assegnazione e l'avanzamento dei compiti sono gestiti attraverso una **GitHub Project Board (Kanban)** integrata con le GitHub Issues:
* Ogni issue descrive dettagliatamente i requisiti del task, i criteri di accettazione e include l'assegnazione del responsabile (*Assignee*).
* Le schede transitano visivamente attraverso le colonne: *Product Backlog -> To Do -> In Progress -> In Review -> Done*.
* A consuntivo dello sviluppo, l'utilizzo degli strumenti di Configuration Management registra le seguenti metriche di cooperazione:
  * **Issue aperte e chiuse:** ~15 issue tracciate.
  * **Pull Request revisionate e integrate:** ~8 PR con review formale approvata.
  * **Commit totali:** Distribuiti in modo equilibrato tra tutti i 4 componenti del gruppo.

---

## 2.3 People Management and Team Organization

Essendo un team composto da 4 persone, è stato fondamentale formalizzare un'organizzazione del lavoro rigorosa per valorizzare le competenze, prevenire sovrapposizioni e garantire un contributo quantitativo e qualitativo equo da parte di tutti i membri.

### Modello Organizzativo del Team
Facendo riferimento ai modelli organizzativi formali della letteratura di Ingegneria del Software, il gruppo ha adottato la struttura di un **Democratic Decentralized Team (DD)**:
* **Leadership Decentralizzata:** Non esiste un responsabile unico gerarchico; le decisioni architetturali di alto livello (definizione dei pattern, struttura dei layer, convenzioni di codifica) vengono discusse e approvate collegialmente su base democratica.
* **Canali di Comunicazione a Grafo Completo:** La comunicazione avviene in modo orizzontale, trasparente e diretto tra tutti i membri, massimizzando lo scambio informativo e la tempestività nella risoluzione dei problemi.
* **Specializzazione dei Ruoli Operativi:** Per garantire l'efficienza della WBS e soddisfare il requisito di chiarezza sui contributi individuali, sono state assegnate responsabilità primarie ben distinte.

### Ruoli e Responsabilità Individuali

* **Franchini Mattia – Project Coordinator & Core Developer:**
  * Responsabile primario del coordinamento generale dello scheduling, monitoraggio delle milestone e allineamento del Project Plan.
  * Progettazione e implementazione della logica di business nel modulo `MED_core` (interfacce di servizio, coordinamento del ciclo di vita con lo State Pattern).
  * Gestione e supervisione dell'integrazione tra il layer Core e il modulo di persistenza Database.
  * Manutenzione, refactoring del codice sorgente e revisione della documentazione tecnica.
  * Co-progettazione, sviluppo e testing dell'interfaccia utente grafica (`MED_gui`).

* **Ghislanzoni Fabio – Business Logic Developer & Requirements Analyst:**
  * Responsabile primario della redazione, formalizzazione e finalizzazione del **Documento 3 (Requisiti del Sistema & Qualità del Software)** secondo standard IEEE 830.
  * Implementazione, collaudo e manutenzione dei servizi applicativi in `MED_core`.

* **Mazzoleni Lino – Configuration Manager & UI Developer:**
  * Responsabile primario dell'amministrazione del repository GitHub, definizione delle regole di branching, gestione della Kanban Board.
  * Modellazione dei diagrammi statici e dinamici su StarUML v5 e coordinamento della generazione codice con Javazzar.
  * Progettazione, sviluppo, test di usabilità e manutenzione delle classi di interfaccia utente (`MED_gui`).

* **Ivasco Riccardo – Requirements Engineer & Database Specialist:**
  * Elicitazione e formalizzazione delle specifiche dei requisiti funzionali e non funzionali, stesura delle tabelle dei casi d'uso (Use Case Specifications).
  * Progettazione dello schema relazionale del database embedded SQLite, implementazione del pattern Singleton e dei componenti in `MED_database`.
  * Scrittura dei test di integrazione per il layer di persistenza e gestione della portabilità dei percorsi relativi del file `.db`.

*(Nota: in conformità alle buone pratiche di team, tutti i componenti hanno partecipato attivamente alla scrittura del codice Java, alla redazione dei test unitari JUnit 5 nei rispettivi moduli e alle attività di Peer Review sulle Pull Request).*

### Modalità di Coordinamento e Comunicazione
Il coordinamento operativo del gruppo è avvenuto combinando metodologie sincrone e asincrone:
* **Sessioni in Presenza:** Incontri settimanali presso le aule studio e i laboratori dell'Ateneo per sessioni di progettazione architetturale congiunta, analisi dei diagrammi StarUML e aggiornamento della Kanban Board.
* **Comunicazione Remota & Stand-up Meeting:** Brevi allineamenti bisettimanali su piattaforma Discord per il monitoraggio dei task *In Progress* e l'identificazione precoce di colli di bottiglia (*blockers*).
* **Pair Programming e Code Review Condivise:** Utilizzo frequente dello screen-sharing per risolvere congiuntamente bug complessi e discutere l'approvazione delle Pull Request più delicate.
* **Valutazione tra Pari (Peer Review Privata):** Il team ha mantenuto un tracciamento rigoroso e trasparente dell'impegno di ciascuno, garantendo un apporto orario equo (~35-40 ore pro capite) e pienamente verificabile dai log di GitHub.