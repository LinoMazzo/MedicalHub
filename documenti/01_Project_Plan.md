# DOCUMENTO 1: PROJECT PLAN

**Progetto:** MedicalHub  
**Corso:** Ingegneria del Software (AA 2026/27)  
**Docenti:** Prof. Angelo Gargantini, Dott.ssa Silvia Bonfanti  
**Università degli Studi di Bergamo**  
**Team di Sviluppo (4 componenti):**
* Franchini Mattia (Matr. 1099581)
* Ghislanzoni Fabio (Matr. 1099423)
* Mazzoleni Lino (Matr. 1098962)
* Ivasco Riccardo (Matr. 1101140)

---

### Storico delle Versioni
| Versione | Data | Autori | Descrizione delle modifiche |
| :--- | :--- | :--- | :--- |
| **1.0.0** | 01/10/2026 | Team MedicalHub | Versione iniziale del Project Plan |
| **1.0.1** | 03/10/2026 | Franchini Mattia | Aggiunta quarto membro e ricalibrazione budget/tempistiche |
| **1.1.0** | 05/11/2026 | Mazzoleni Lino | Dettaglio ruoli individuali, analisi rischi operativi, tassonomia della qualità di McCall, standard IEEE 830 e WBS completa |
| **1.1.1** | 06/11/2026 | Mazzoleni Lino, Franchini Mattia | Migliorie generali al documento |
| **1.1.2** | 06/11/2026 | Mazzoleni Lino | Minor updates al documento |


## 1. Introduzione

* **Background & Motivazione:** La riabilitazione post-operatoria e la medicina dello sport comportano percorsi terapeutici complessi che richiedono il coordinamento continuo tra medici, fisioterapisti e atleti. L'attuale gestione tramite registri cartacei o fogli di calcolo disgiunti provoca asincronie informative, conflitti nell'uso di macchinari specialistici (es. Tecar, idrokinesi) e una quantificazione poco oggettiva dei progressi di recupero articolare (ROM).
* **Obiettivo:** Progettare e sviluppare **MedicalHub**, una piattaforma software per digitalizzare l'intero ciclo terapeutico: dalla diagnosi iniziale con assegnazione del piano, alla prenotazione vincolata delle sedute, fino al monitoraggio oggettivo del dolore e dei gradi articolari per il rilascio dell'idoneità agonistica.
* **Risultati Attesi (Deliverables):**
  1. Relazione tecnica completa strutturata secondo le linee guida (Documenti 1-6).
  2. Modelli UML completi realizzati e archiviati tramite StarUML (`.mdj`).
  3. Applicazione Java sviluppata in ambiente Eclipse con gestione dipendenze tramite Apache Maven.
  4. Suite di test di unità JUnit 5 con report quantitativi di code coverage.
  5. Repository GitHub collaborativo con storico completo e tracciabile di issue, branch, pull request e revisioni tra pari.


## 2. Modello di Processo

* **Modello Adottato:** Modello **Agile basato su Scrum / Iterativo-Incrementale a Timebox**, ideale per gestire l'affinamento progressivo dei requisiti e la validazione continua dei moduli di dominio.
* **Articolazione in Sprint:** Lo sviluppo è suddiviso in 4 Sprint della durata di 2 settimane ciascuno:
  * **Sprint 1 (Milestone M1):** Definizione del perimetro del problema, elicitazione requisiti, redazione del Project Plan (Doc 1) e approvazione formale dal docente.
  * **Sprint 2 (Milestone M2):** Specifica dei requisiti (Doc 3, IEEE 830), Use Case Diagram, Class Diagram e modellazione dinamica (State Machine) su StarUML. Generazione dello scheletro del codice.
  * **Sprint 3 (Milestone M3):** Implementazione logica di business (Service layer), integrazione del database embedded SQLite, prime interfacce utente e test JUnit del core applicativo.
  * **Sprint 4 (Milestone M4):** Completamento UI, analisi statica del codice (metriche di accoppiamento e complessità), refactoring antipattern, finalizzazione documentazione e preparazione slide.
* **Criteri di Avanzamento:** Ciascuna milestone è dichiarata completata solo al soddisfacimento della *Definition of Done* (DoD): codice compilabile senza warning bloccanti, test unitari verdi, documentazione allineata e Pull Request approvata collegialmente.


## 3. Organizzazione del Progetto

* **Relazione con gli Stakeholder:** Il team modella il sistema basandosi su protocolli clinici e scenari reali di recupero post-operatorio (es. ricostruzione legamento crociato anteriore), validando la verosimiglianza di flussi e vincoli.
* **Modello Organizzativo:** Si adotta il modello **Democratic Decentralized (DD)** descritto dalla letteratura di ingegneria del software (Van Vliet): la leadership delle decisioni architetturali è condivisa e orizzontale, mentre le responsabilità operative sono specializzate per massimizzare l'efficienza.
* **Ruoli e Compiti Individuali nel Team (4 componenti):**
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

*(Nota: tutti i membri partecipano attivamente alla scrittura del codice Java, alla redazione dei casi di test JUnit 5 e alle Code Review sulle Pull Request).*


## 4. Standard, Linee Guida, Procedure

* **Standard di Codifica:** *Oracle Java Code Conventions* (stile camelCase, visibilità restrittiva dei campi, assenza di dipendenze cicliche tra package).
* **Specifiche dei Requisiti:** Standard **IEEE 830** (*Recommended Practice for Software Requirements Specifications*), con identificazione gerarchica e univoca di requisiti funzionali (es. `REQ-F-01`) e non funzionali (es. `REQ-NF-01`).
* **Modellazione UML:**  UML mediante tool ufficiale **StarUML v5**.
* **Controllo Versione (Feature-Branch Workflow):**
  * **Branch `main`:** Ramo protetto (Branch Protection Rule attiva). Contiene esclusivamente versioni stabili, collaudate e conformi alle release (tag `v1.0.0`, ecc.). Vietato il commit diretto e il force-push.
  * **Branch `develop`:** Ramo principale di integrazione continua, su cui confluiscono tutti i contributi verificati.
  * **Branch `feature/<nome-feature>`:** Rami temporanei di lavoro creati a partire da `develop` per isolare singoli task (es. `feature/db-sqlite-layer`, `feature/doc3-ieee830`).
  * **Pull Request (PR) & Code Review:** L'integrazione di una feature su `develop` avviene esclusivamente tramite PR. È richiesta l'approvazione obbligatoria (Approve) di almeno un compagno dopo verifica delle modifiche e dei test.
  * **Convenzione Commit:** Stile *Conventional Commits* (es. `feat:`, `fix:`, `docs:`, `test:`, `refactor:`).


## 5. Attività di Gestione

Le attività di gestione del progetto sono orientate a garantire il rispetto delle scadenze prefissate, il mantenimento di elevati standard qualitativi del codice e un bilanciamento continuo tra requisiti, tempi di consegna e impegno orario dei componenti.

#### 5.1 Monitoraggio dell'Avanzamento e Strumenti di Controllo
Il monitoraggio operativo e temporale è strutturato tramite l'ecosistema GitHub:
* **GitHub Project Board (Kanban):** Il flusso di lavoro segue una pipeline visiva strutturata in 5 stati operativi:
  1. *Product Backlog:* raccolta generale di requisiti e idee future.
  2. *To Do:* task selezionati e pianificati per lo Sprint corrente.
  3. *In Progress:* task presi in carico (massimo 1 task attivo per componente, per limitare il Work-In-Progress - WIP).
  4. *In Review:* funzionalità completate in attesa di Code Review su Pull Request.
  5. *Done:* task collaudati e fusi nel ramo di integrazione.

#### 5.2 Comunicazione Interna e Cerimonie del Team
Per garantire un allineamento costante e trasparente, il gruppo adotta cerimonie tipiche del framework Agile/Scrum:
* **Stand-up Meeting Bisettimanali:** Brevi incontri di 15-20 minuti (in presenza o tramite Discord con screen-sharing) focalizzati su tre quesiti: *cosa è stato fatto*, *cosa si farà fino al prossimo meeting* e *quali ostacoli bloccano il flusso*.
* **Sprint Review & Retrospective:** Al termine di ogni iterazione di 2 settimane, il team valida collegialmente i deliverable completati e analizza retrospettivamente il processo di sviluppo per apportare miglioramenti operativi nello sprint successivo.
* **Tracciamento Decisionale:** Le decisioni architetturali rilevanti (es. adozione di librerie, modifiche allo schema del database) vengono formalizzate e documentate direttamente nei thread delle Issue o delle Pull Request.

#### 5.3 Criteri di Completamento (Definition of Done - DoD)
Nessun task o funzionalità può transitare nella colonna *Done* della Kanban Board né essere integrato in `develop` se non soddisfa congiuntamente i seguenti criteri formali:
1. Il codice compila senza errori ed è formattato secondo le *Oracle Java Code Conventions*.
2. È stata implementata la relativa suite di test di unità con JUnit 5 con esito positivo (build Maven verde: `mvn clean test`).
3. L'architettura rispetta il vincolo di aciclicità tra i package (`MED_core`, `MED_database`, `MED_gui`).
4. La relativa Pull Request è stata esaminata, commentata e approvata formalmente da almeno un compagno di gruppo (Peer Review).
5. La documentazione correlata (diagrammi StarUML o specifiche dei requisiti) è stata debitamente aggiornata.


## 6. Gestione dei Rischi

L'analisi preventiva identifica i rischi concreti legati allo sviluppo accademico e al dominio specialistico:

| ID | Descrizione del Rischio | Prob. | Impatto | Strategia di Mitigazione Preventiva | Piano di Contingenza (Se il rischio si verifica) |
|---|---|---|---|---|---|
| **R1** | **Lacune nel dominio specialistico:** Scarsa familiarità iniziale con i protocolli clinici riabilitativi, la misurazione del ROM articolare e la scala VAS del dolore. | Media | Medio | Studio preliminare di casi clinici reali post-operatori e consultazione di documentazione medica aperta; redazione di un glossario clinico condiviso in Doc 3. | Semplificazione delle metriche cliniche adottando modelli standardizzati a livello internazionale (es. test di flesso-estensione a gradi discreti). |
| **R2** | **Concomitanza con sessioni d'esame:** Sovrapposizione di appelli d'esame o scadenze universitarie dei membri del team, con conseguente riduzione della disponibilità oraria. | Alta | Alto | Pianificazione temporale anticipata; buffer di sicurezza di 10 giorni sul cronoprogramma; milestone intermedie non concentrate nelle settimane d'esame. | Riassegnazione temporanea delle issue non bloccanti tra i membri liberi, applicando la prioritizzazione MoSCoW sui soli requisiti *Must-Have*. |
| **R3** | **Inesperienza con i tool accademici:** Difficoltà tecniche nell'uso di StarUML per la generazione codice Java, plugin Maven o configurazione del DB SQLite embedded. | Media | Medio | Spike tecnici individuali preliminari (prototipi "usa-e-getta" nella prima settimana) per validare la catena di generazione codice e persistenza. | Supporto reciproco tramite screen-sharing su Discord; eventuale rifinitura manuale delle classi generate da StarUML. |
| **R4** | **Conflitti di Merge su Git:** Conflitti tra branch dovuti allo sviluppo concorrente da parte di 4 persone sulla stessa base di codice. | Media | Medio | Decomposizione modulare rigida del codice in layer e package separati; pull frequenti da `develop`; divieto di modificare classi assegnate ad altri senza accordo. | Risoluzione collegiale a due del conflitto in sessione dedicata prima di consentire il merge su `develop`. |
| **R5** | **Indisponibilità temporanea di un componente:** Malattia o impedimenti personali improvvisi che bloccano una componente critica. | Bassa | Medio | Tracciamento trasparente dello stato d'avanzamento su GitHub Issue e documentazione del codice in progress; condivisione continua delle scelte tecniche. | Riassegnazione immediata del task bloccato grazie alla presenza di ruoli secondari intercambiabili. |


## 7. Personale

La pianificazione del personale definisce il dimensionamento del team, l'attribuzione delle responsabilità specialistiche, la distribuzione temporale dello sforzo lavorativo lungo l'intero ciclo di vita del progetto e le attività di formazione necessarie per colmare eventuali gap tecnici.

#### 7.1 Dimensionamento e Ruoli Operativi
Il team è composto da **4 persone**. In conformità al modello *Democratic Decentralized (DD)* adottato, ogni componente è responsabile primario di una specifica area ingegneristica, pur collaborando attivamente all'implementazione del codice e alle attività di code review collegiale:

#### 7.2 Stima e Ripartizione Temporale dello Sforzo (Effort Allocation)
* **Budget Temporale Complessivo:** Il progetto prevede un impegno stimato di circa **35-40 ore a persona**, per un totale aggregato di circa **150-160 ore-uomo**, distribuite su un arco temporale di **8 settimane** (~4.5 - 5 ore a testa a settimana).
* **Profilo Temporale del Personale (Carico per Fasi):** L'impegno dei componenti varia dinamicamente in base alla fase del ciclo di vita:
  * *Fase Iniziale (Settimane 1–2 - Requisiti & Planning):* Massimo sforzo per il Requirements Engineer e l'Architect per la definizione del dominio e la sottomissione del Project Plan. Sforzo iniziale per il Build Engineer (setup repository e Maven).
  * *Fase Centrale (Settimane 3–5 - Design & Core Development):* Massimo impegno per l'Architect e il Database Specialist per la progettazione dettagliata, la State Machine, la creazione delle tabelle SQLite e lo scheletro delle classi Java.
  * *Fase di Completamento e Collaudo (Settimane 6–8 - Testing, QA & Final Delivery):* Massimo sforzo per il Test Engineer e il QA Manager per l'esecuzione della suite JUnit, la verifica della copertura, la misurazione delle metriche del codice e la finalizzazione dei 6 documenti della relazione.

| Ruolo / Attività | Settimane 1-2 (Requisiti/Plan) | Settimane 3-5 (Architettura/Sviluppo) | Settimane 6-8 (Testing/Delivery) | Totale Ore |
| :--- | :---: | :---: | :---: | :---: |
| **Requirements & Planning** | 22 h | 8 h | 8 h | **38 h** |
| **Architecture & Design** | 12 h | 20 h | 6 h | **38 h** |
| **Database & Development** | 8 h | 22 h | 8 h | **38 h** |
| **Testing & Quality Assurance** | 6 h | 12 h | 20 h | **38 h** |
| **Totale Ore-Uomo** | **48 h** | **62 h** | **42 h** | **152 h** |

#### 7.3 Piano di Formazione e Colmamento dei Gap (Training Plan)
Per garantire l'allineamento delle competenze tecniche necessarie alla realizzazione del software, il team ha pianificato percorsi di auto-formazione e studio mirato nelle prime 2 settimane:
1. **Linguaggio Java Avanzato:** Approfondimento delle funzionalità moderne di Java 17.
2. **Framework di Testing JUnit 5:** Studio delle asserzioni complesse, gestione delle fixture e configurazione dei plugin Maven Surefire per il reporting.
3. **Database Embedded SQLite & JDBC:** Studio della sintassi SQL di SQLite, gestione della concorrenza del database su singolo file locale, implementazione del pattern Singleton per prevenire leak di connessioni e gestione dei percorsi relativi (senza path assoluti al filesystem).
4. **Strumenti di Progettazione e Metriche:** Esercitazioni pratiche sull'uso di **StarUML v5** (creazione di State Machine, Sequence e Class Diagram con generazione del codice) e apprendimento delle metriche di modularità (complessità ciclomatica di McCabe e suite di metriche C&K tramite StanIDE/JDepend).

#### 7.4 Continuità Operativa e Meccanismi di Backup
Nel caso in cui un membro del team sia temporaneamente indisponibile o impossibilitato a completare un task critico:
* La tracciabilità dettagliata delle specifiche e lo storico delle issue su GitHub garantiscono che un altro componente possa subentrare nell'attività senza perdita di informazioni.
* Non sono ammesse dipendenze esclusive: ogni modulo è compreso da almeno due persone grazie al meccanismo obbligatorio delle Pull Request e delle Code Review incrociate.

## 8. Metodi e Tecniche

Questa sezione descrive le metodologie ingegneristiche, i linguaggi, gli ambienti di prova e gli strumenti operativi adottati durante l'intero ciclo di vita del software.

#### 8.1 Ingegneria dei Requisiti
* **Elicitazione:** Analisi di dominio orientata ai processi riabilitativi ortopedici reali, supportata da interviste a stakeholder (fisioterapisti e atleti) e stesura di scenari d'uso tipici ed eccezionali.
* **Specifica Formale (SRS):** Redazione della specifica dei requisiti software secondo lo standard **IEEE 830**, strutturata con identificatori univoci e gerarchici (es. `REQ-F-xx` per i funzionali, `REQ-NF-xx` per i non funzionali) per garantire la matrice di tracciabilità verso i casi di test.

#### 8.2 Modellazione Orientata agli Oggetti (OOAD)
La modellazione concettuale e dettagliata è realizzata tramite **StarUML v5** adottando la notazione standard UML 2.x:
* **Viste Statiche:** Class Diagram di dominio (struttura delle entità cliniche), Package Diagram (organizzazione dei moduli) e Component Diagram (architettura del sistema).
* **Viste Dinamiche:** 
  * *Use Case Diagram:* interazione tra gli attori (Paziente, Medico, Fisioterapista, Desk) e i servizi del sistema.
  * *State Machine Diagram:* modellazione dettagliata e non banale del ciclo di vita del Piano Riabilitativo (da Bozza a Idoneità Rilasciata).
  * *Sequence & Activity Diagram:* formalizzazione dei flussi di prenotazione congiunta (risorse/operatori) e della gestione delle anomalie cliniche.
* **Generazione del Codice:** Adozione dell'approccio *Model-Driven* parziale, generando la struttura base delle classi Java e le relative firme dei metodi direttamente dal Class Diagram di StarUML tramite l'estensione Javazzar.

#### 8.3 Sviluppo Software e Build Automation
* **Linguaggio e Paradigma:** Programmazione orientata agli oggetti avanzata in **Java 17 LTS**, con uso intensivo del Collections Framework.
* **Stile Architetturale:** Adozione di un'architettura **3-Tier / a Layer disaccoppiati**, suddivisa su **3 progetti Maven distinti**:
  * `MED_core`: logica di business e modello di dominio.
  * `MED_database`: strato di accesso ai dati e persistenza.
  * `MED_gui`: strato di presentazione e interazione utente.
* **Persistenza Dati:** Utilizzo del database relazionale embedded **SQLite**, integrato tramite driver JDBC (`sqlite-jdbc`). La configurazione adotta esclusivamente percorsi relativi per garantire la totale portabilità del file di database (`medicalhub.db`) su qualsiasi macchina senza dipendenze da percorsi assoluti.
* **Build Automation:** Gestione unificata di compilazione, dipendenze e packaging tramite **Apache Maven**.

#### 8.4 Controllo di Versione e Gestione della Documentazione
* **Configuration Management:** Adozione del sistema **Git** ospitato su **GitHub**, governato da un *Feature-Branch Workflow* (divieto di commit diretti su `main`, integrazione su `develop` tramite Pull Request con approvazione obbligatoria).
* **Cura della Documentazione:** Redazione dei deliverable in formato **Markdown** (versionati direttamente nel repository sotto `/documenti`) ed esportazione finale consolidata in documento PDF unico organizzato a capitoli. Il modello sorgente StarUML (`.mdj`) è mantenuto sincronizzato su GitHub.


## 9. Garanzia di Qualità (Quality Assurance)

La qualità del sistema è governata formalmente attraverso la **Tassonomia della Qualità di McCall**, articolata sulle tre dimensioni fondamentali:

### 1. Product Operations (Operazioni sul Prodotto)
* **Correttezza (Correctness):** Il sistema soddisfa appieno i requisiti clinici funzionali.  
  *Verifica:* Esecuzione automatizzata della suite di test JUnit 5.
* **Affidabilità (Reliability):** Il software gestisce stati d'errore o input incongrui senza crash imprevisti.  
  *Verifica:* Incapsulamento delle anomalie in eccezioni di dominio specifiche (`InvalidPlanStateException`, `ResourceConflictException`).
* **Efficienza (Efficiency):** Utilizzo ottimizzato delle risorse computazionali e tempi di risposta istantanei.  
  *Verifica:* Uso di un database relazionale embedded SQLite in-memory/file locale leggero, privo di overhead di rete o processi esterni.
* **Integrità (Integrity):** Protezione dei dati clinici da modifiche non autorizzate o inconsistenti.  
  *Verifica:* Separazione dei privilegi tramite modello dei ruoli utente.
* **Usabilità (Usability):** Facilità di interazione e chiarezza nella gestione delle sedute e delle agende.  
  *Verifica:* Ispezione euristica dell'interfaccia utente e test di usabilità guidati con scenari tipici.

### 2. Product Revision (Revisione e Manutenibilità)
* **Manutenibilità (Maintainability):** Semplicità nel localizzare e correggere difetti o modificare regole di business.  
* **Testabilità (Testability):** Semplicità nell'isolare e collaudare le singole unità logiche.  

### 3. Product Transition (Transizione e Portabilità)
* **Portabilità (Portability):** Funzionamento trasparente e identico su diverse piattaforme operative (Windows, macOS, Linux).  
  *Verifica:* Utilizzo esclusivo di JDK standard 17+, build gestita interamente tramite `pom.xml` Maven, divieto assoluto di percorsi di file assoluti nel codice.
* **Interoperabilità (Interoperability):** Predisposizione del software a dialogare con strumenti esterni o database standard.  
  *Verifica:* Strutturazione dei dati conforme a dialetto standard SQL.


## 10. Pacchetti di Lavoro (Work Breakdown Structure - WBS)

La scomposizione gerarchica delle attività del progetto assegna responsabilità chiare a ciascun componente:

* **WP1 – Project Management & Configuration Setup** *(Resp: Franchini, Mazzoleni, Ghislanzoni)*
  * WP1.1: Stesura iniziale e revisioni progressive del Project Plan (Doc 1).
  * WP1.2: Inizializzazione repository GitHub, branch, `.gitignore` e Kanban Board.
  * WP1.3: Definizione convenzioni di codifica, template di issue e modello di branching.
* **WP2 – Requirements Engineering & Software Quality** *(Resp: Ivasco)*
  * WP2.1: Elicitazione dei requisiti clinici.
  * WP2.2: Stesura documento di specifica requisiti software (Doc 3) secondo standard IEEE 830.
  * WP2.3: Definizione delle metriche di qualità del software e conformità al modello McCall.
* **WP3 – Modelling & System Design** *(Resp: Mazzoleni, Franchini)*
  * WP3.1: Modellazione dei Casi d'Uso (Use Case Diagram e Use Case Specifications).
  * WP3.2: Modellazione statica (Class Diagram di dominio) e generazione codice tramite StarUML/Javazzar.
  * WP3.3: Modellazione dinamica: State Machine Diagram (ciclo piano), Sequence e Activity Diagram.
  * WP3.4: Stesura Documento 4 (Architettura e Design, pattern e viste architetturali).
* **WP4 – Software Implementation** *(Resp: Tutti i componenti)*
  * WP4.1: *Database Layer:* Configurazione database embedded (SQLite) *(Ivasco)*.
  * WP4.2: *Core & Service Layer:* Logica del piano riabilitativo, controlli clinici e algoritmi di valutazione *(Franchini, Ghislanzoni)*.
  * WP4.3: *User Interface Layer:* Sviluppo interfacce utente e gestione input operatore *(Mazzoleni, Franchini)*.
* **WP5 – Verification, Testing & Static Analysis** *(Resp: Tutti i componenti)*
  * WP5.1: Progettazione e stesura della suite di test unitari JUnit 5 su DB, Service e UI.
  * WP5.2: Misurazione della copertura del codice (Code Coverage) e test sui casi limite.
  * WP5.3: Calcolo analitico della complessità ciclomatica di McCabe e verifica accoppiamento package.
  * WP5.4: Stesura Documento 5 (Testing e Validazione).
* **WP6 – Refactoring, Maintenance & Final Delivery** *(Resp: Tutti i componenti)*
  * WP6.1: Ispezione e rimozione di eventuali antipattern (Doc 6).
  * WP6.2: Finalizzazione Documento 2 (Software Life Cycle, SCM, Team Organization).
  * WP6.3: Consolidamento relazione generale e congelamento release sul branch `main`.
  * WP6.4: Allestimento slide della presentazione orale e predisposizione ambiente per la demo live.


## 11. Risorse

* **Risorse Hardware:** 4 personal computer portatili utilizzati dai componenti del team per lo sviluppo e il collaudo locale.
* **Risorse Software & Strumenti:**
  * **Linguaggio & SDK:** Java Development Kit (JDK) versione 17+.
  * **IDE:** Eclipse IDE for Java Developers (versione 2024/2026).
  * **Build & Dependency Management:** Apache Maven 3.9+.
  * **DBMS:** SQLite Database Engine (modalità embedded locale, zero installazioni esterne).
  * **Modellazione UML & Code Gen:** StarUML v5 con estensione Javazzar.
  * **Collaboration & Tracking:** GitHub (Git repository, Pull Requests, Issues, Projects Kanban), Discord per meeting di allineamento e screen-sharing.


## 12. Budget e Programma Temporale

* **Budget Totale:** Circa **160 ore-uomo** complessive ripartite equamente tra i 4 componenti (~40 ore pro capite). Nessun budget economico monetario richiesto (utilizzo esclusivo di tool e librerie open-source/gratuite).
* **Cronoprogramma delle Milestone:**
  * **Settimana 1-2 (Sprint 1 - Fine Ottobre):** Conclusione WP1 e avvio WP2. Consegna e sottomissione del Project Plan iniziale (Doc 1) per approvazione del docente tramite GitHub Issue $\rightarrow$ **Milestone M1**.
  * **Settimana 3-4 (Sprint 2 - Metà Novembre):** Conclusione WP2 (Doc 3 IEEE 830) e WP3 (Modellazione StarUML, Class & State Diagram, prima generazione codice Java) $\rightarrow$ **Milestone M2**.
  * **Settimana 5-6 (Sprint 3 - Inizio Dicembre):** Avanzamento WP4 (Sviluppo Core, Service, SQLite DB, UI) e WP5 (Prime classi di test JUnit 5) $\rightarrow$ **Milestone M3**.
  * **Settimana 7-8 (Sprint 4 - Fine Dicembre/Pre-esame):** Conclusione WP5 (Coverage e calcolo metriche McCabe), WP6 (Doc 2, Doc 5, Doc 6, refactoring).  


## 13. Gestione dei Cambiamenti (Change Management)

* Qualsiasi modifica strutturale ai requisiti, all'architettura o alla pianificazione originaria deve essere formalizzata mediante l'apertura di una **GitHub Issue** etichettata con il tag `change-request`.
* Le richieste di modifica vengono analizzate collegialmente durante gli stand-up meeting per valutarne l'impatto tecnico, le dipendenze e l'aggravio in ore-uomo.
* In caso di approvazione, la modifica viene recepita nella documentazione tecnica e tracciata con incremento del numero di versione nella tabella del **Registro delle Modifiche** posta all'inizio di ciascun documento.


## 14. Consegna

* **Condivisione Repository:** Repository GitHub condiviso con accesso collaboratore concesso ai docenti (`garganti`, `silviabonfanti`).
* **Artefatti di Consegna (Deliverables):**
  1. Cartella `codice/`: Crtella contenente i tre progetti maven (MED_database, MED_core, MED_gui).
  2. Cartella `documenti/`: Documentazione tecnica completa suddivisa nei 6 documenti in formato Markdown/PDF conformi alle linee guida.
  3. Cartella `documenti/models/`: File di progetto sorgente `.mdj` di StarUML contenente tutti i diagrammi realizzati.
* **Presentazione Orale:**
  * Esposizione tramite slide (utilizzando il template ufficiale fornito dai docenti) della durata massima di **35 minuti per team da 4 componenti** (+ circa 10 minuti dedicati a domande e osservazioni della commissione).
  * Esecuzione di una demo live su personal computer per mostrare l'esecuzione dell'applicazione e della suite di test unitari.