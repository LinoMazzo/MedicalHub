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
| **1.1.0** | 05/11/2026 | Mazzoleni Lino | Dettaglio ruoli individuali, analisi rischi operativi, tassonomia della qualità di McCall, standard IEEE 830/1016 e WBS completa |

---

## 1. Introduzione

* **Background & Motivazione:** La riabilitazione post-operatoria e la medicina dello sport comportano percorsi terapeutici complessi che richiedono il coordinamento continuo tra medici, fisioterapisti e atleti. L'attuale gestione tramite registri cartacei o fogli di calcolo disgiunti provoca asincronie informative, conflitti nell'uso di macchinari specialistici (es. Tecar, idrokinesi) e una quantificazione poco oggettiva dei progressi di recupero articolare (ROM).
* **Obiettivo:** Progettare e sviluppare **MedicalHub**, una piattaforma software per digitalizzare l'intero ciclo terapeutico: dalla diagnosi iniziale con assegnazione del piano, alla prenotazione vincolata delle sedute, fino al monitoraggio oggettivo del dolore e dei gradi articolari per il rilascio dell'idoneità agonistica.
* **Risultati Attesi (Deliverables):**
  1. Relazione tecnica completa strutturata secondo le linee guida (Documenti 1-6).
  2. Modelli UML completi realizzati e archiviati tramite StarUML (`.mdj`).
  3. Applicazione Java sviluppata in ambiente Eclipse con gestione dipendenze tramite Apache Maven.
  4. Suite di test di unità JUnit 5 con report quantitativi di code coverage.
  5. Repository GitHub collaborativo con storico completo e tracciabile di issue, branch, pull request e revisioni tra pari.

---

## 2. Modello di Processo

* **Modello Adottato:** Modello **Agile basato su Scrum / Iterativo-Incrementale a Timebox**, ideale per gestire l'affinamento progressivo dei requisiti e la validazione continua dei moduli di dominio.
* **Articolazione in Sprint:** Lo sviluppo è suddiviso in 4 Sprint della durata di 2 settimane ciascuno:
  * **Sprint 1 (Milestone M1):** Definizione del perimetro del problema, elicitazione requisiti, redazione del Project Plan (Doc 1) e approvazione formale dal docente.
  * **Sprint 2 (Milestone M2):** Specifica dei requisiti (Doc 3, IEEE 830), Use Case Diagram, Class Diagram e modellazione dinamica (State Machine) su StarUML. Generazione dello scheletro del codice.
  * **Sprint 3 (Milestone M3):** Implementazione logica di business (Service layer), integrazione del database embedded H2, prime interfacce utente e test JUnit del core applicativo.
  * **Sprint 4 (Milestone M4):** Completamento UI, analisi statica del codice (metriche di accoppiamento e complessità), refactoring antipattern, finalizzazione documentazione e preparazione slide.
* **Criteri di Avanzamento:** Ciascuna milestone è dichiarata completata solo al soddisfacimento della *Definition of Done* (DoD): codice compilabile senza warning bloccanti, test unitari verdi, documentazione allineata e Pull Request approvata collegialmente.

---

## 3. Organizzazione del Progetto

* **Relazione con gli Stakeholder:** Il team modella il sistema basandosi su protocolli clinici e scenari reali di recupero post-operatorio (es. ricostruzione legamento crociato anteriore), validando la verosimiglianza di flussi e vincoli.
* **Modello Organizzativo:** Si adotta il modello **Democratic Decentralized (DD)** descritto dalla letteratura di ingegneria del software (Van Vliet): la leadership delle decisioni architetturali è condivisa e orizzontale, mentre le responsabilità operative sono specializzate per massimizzare l'efficienza.
* **Ruoli e Compiti Individuali nel Team (4 componenti):**
  * **Franchini Mattia – *Project Coordinator & Core Developer*:** Coordinamento generale dello scheduling e delle modifiche al Project Plan; progettazione e implementazione delle interfacce del livello di servizio (Service Layer) e delle interfacce di persistenza verso il database; gestione e revisione della documentazione tecnica; manutenzione e refactoring del codice core.
  * **Ghislanzoni Fabio – *Business Logic Developer & Requirements Analyst*:** Redazione e finalizzazione del **Documento 3 (Requisiti del Sistema & Qualità del Software)**; implementazione, collaudo e manutenzione del livello di servizio applicativo; co-progettazione, testing e manutenzione dell'interfaccia utente (UI).
  * **Mazzoleni Lino – *Configuration Manager & UI Developer*:** Inizializzazione e amministrazione del repository GitHub (branching, policy di protezione); supporto alle revisioni del Project Plan; modellazione dei diagrammi UML su StarUML e coordinamento della generazione codice; stesura, unit testing e manutenzione dell'interfaccia utente.
  * **Ivasco Riccardo – *Requirements Engineer & Database Specialist*:** Elicitazione e redazione formale delle specifiche dei requisiti funzionali/non funzionali (Documento 3, standard IEEE 830); progettazione logica, implementazione, collaudo e manutenzione del layer di persistenza (Database embedded H2).

*(Nota: tutti i membri partecipano attivamente alla scrittura del codice Java, alla redazione dei casi di test JUnit 5 e alle Code Review sulle Pull Request).*

---

## 4. Standard, Linee Guida, Procedure

* **Standard di Codifica:** *Oracle Java Code Conventions* (stile camelCase, visibilità restrittiva dei campi, assenza di dipendenze cicliche tra package).
* **Specifiche dei Requisiti:** Standard **IEEE 830-1998** (*Recommended Practice for Software Requirements Specifications*), con identificazione gerarchica e univoca di requisiti funzionali (es. `REQ-F-01`) e non funzionali (es. `REQ-NF-01`).
* **Documentazione del Design:** Standard **IEEE 1016** (*Software Design Descriptions*), strutturando la descrizione architetturale per viste (decomposizione, dipendenze, interfacce, dettagli).
* **Modellazione UML:** Standard UML 2.x mediante tool ufficiale **StarUML v5**.
* **Controllo Versione (Feature-Branch Workflow):**
  * **Branch `main`:** Ramo protetto (Branch Protection Rule attiva). Contiene esclusivamente versioni stabili, collaudate e conformi alle release (tag `v1.0.0`, ecc.). Vietato il commit diretto e il force-push.
  * **Branch `develop`:** Ramo principale di integrazione continua, su cui confluiscono tutti i contributi verificati.
  * **Branch `feature/<nome-feature>`:** Rami temporanei di lavoro creati a partire da `develop` per isolare singoli task (es. `feature/db-h2-layer`, `feature/doc3-ieee830`).
  * **Pull Request (PR) & Code Review:** L'integrazione di una feature su `develop` avviene esclusivamente tramite PR. È richiesta l'approvazione obbligatoria (Approve) di almeno un compagno dopo verifica delle modifiche e dei test.
  * **Convenzione Commit:** Stile *Conventional Commits* (es. `feat:`, `fix:`, `docs:`, `test:`, `refactor:`).

---

## 5. Attività di Gestione

* **Tracciamento Avanzamento:** Utilizzo integrato della **GitHub Project Board (Kanban)** con le colonne operative `To Do`, `In Progress`, `In Review` e `Done`, associando ogni scheda a issue dettagliate con assegnatari specifici.
* **Comunicazione Interna:** Stand-up meeting bisettimanali (da remoto su Discord o in presenza) per monitorare lo stato delle task, verificare le dipendenze e risolvere impedimenti (*blockers*).
* **Gestione delle Priorità:** Classificazione dei requisiti e dei task tramite tecnica **MoSCoW** (*Must have, Should have, Could have, Won't have*), per garantire la consegna del nucleo vitale del sistema anche a fronte di vincoli temporali stringenti.

---

## 6. Gestione dei Rischi

L'analisi preventiva identifica i rischi concreti legati allo sviluppo accademico e al dominio specialistico:

| ID | Descrizione del Rischio | Prob. | Impatto | Strategia di Mitigazione Preventiva | Piano di Contingenza (Se il rischio si verifica) |
|---|---|---|---|---|---|
| **R1** | **Lacune nel dominio specialistico:** Scarsa familiarità iniziale con i protocolli clinici riabilitativi, la misurazione del ROM articolare e la scala VAS del dolore. | Media | Medio | Studio preliminare di casi clinici reali post-operatori e consultazione di documentazione medica aperta; redazione di un glossario clinico condiviso in Doc 3. | Semplificazione delle metriche cliniche adottando modelli standardizzati a livello internazionale (es. test di flesso-estensione a gradi discreti). |
| **R2** | **Concomitanza con sessioni d'esame:** Sovrapposizione di appelli d'esame o scadenze universitarie dei membri del team, con conseguente riduzione della disponibilità oraria. | Alta | Alto | Pianificazione temporale anticipata; buffer di sicurezza di 10 giorni sul cronoprogramma; milestone intermedie non concentrate nelle settimane d'esame. | Riassegnazione temporanea delle issue non bloccanti tra i membri liberi, applicando la prioritizzazione MoSCoW sui soli requisiti *Must-Have*. |
| **R3** | **Inesperienza con i tool accademici:** Difficoltà tecniche nell'uso di StarUML per la generazione codice Java, plugin Maven o configurazione del DB H2 embedded. | Media | Medio | Spike tecnici individuali preliminari (prototipi "usa-e-getta" nella prima settimana) per validare la catena di generazione codice e persistenza. | Supporto reciproco tramite screen-sharing su Discord; eventuale rifinitura manuale delle classi generate da StarUML. |
| **R4** | **Conflitti di Merge su Git:** Conflitti tra branch dovuti allo sviluppo concorrente da parte di 4 persone sulla stessa base di codice. | Media | Medio | Decomposizione modulare rigida del codice in layer e package separati; pull frequenti da `develop`; divieto di modificare classi assegnate ad altri senza accordo. | Risoluzione collegiale a due del conflitto in sessione dedicata prima di consentire il merge su `develop`. |
| **R5** | **Indisponibilità temporanea di un componente:** Malattia o impedimenti personali improvvisi che bloccano una componente critica. | Bassa | Medio | Tracciamento trasparente dello stato d'avanzamento su GitHub Issue e documentazione del codice in progress; condivisione continua delle scelte tecniche. | Riassegnazione immediata del task bloccato grazie alla presenza di ruoli secondari intercambiabili. |

---

## 7. Personale

* **Dimensionamento:** 4 persone.
* **Impegno Individuale:** Circa **35-40 ore a persona**, per un totale di circa **150-160 ore-uomo** distribuite su 8 settimane di attività.
* **Formazione Richiesta:**
  * Ripasso avanzato di Java (Collezioni, Generics, Stream API, gestione temporale con `java.time`).
  * Studio del framework di testing **JUnit 5** e librerie di asserzione.
  * Studio dei Design Pattern formali (Lethbridge/Van Vliet) e standard IEEE 830/1016.
  * Studio della configurazione del driver embedded **H2 Database**.

---

## 8. Metodi e Tecniche

* **Ingegneria dei Requisiti:** Elicitazione basata su scenari d'uso, redazione formale SRS conforme a IEEE 830.
* **Modellazione ad Oggetti (OOAD):** Creazione di Use Case Diagram, Class Diagram, State Machine Diagram (ciclo piano riabilitativo), Sequence Diagram, Activity Diagram, Package e Component Diagram tramite **StarUML v5**.
* **Sviluppo:** Programmazione Object-Oriented in **Java 17/21** su IDE **Eclipse**, con architettura a layer disaccoppiati (Model, Database/Repository, Service, UI).
* **Testing:** Suite di collaudo basata su **JUnit 5**, applicando criteri di branch coverage e path coverage sui flussi decisionali critici.
* **Metriche di Progettazione:** Valutazione della complessità ciclomatica di McCabe ($CV = e - n + p + 1$) e analisi dell'accoppiamento dei package ($C_a, C_e, I, A$) secondo le linee guida del Capitolo 12.

---

## 9. Garanzia di Qualità (Quality Assurance)

La qualità del sistema è governata formalmente attraverso la **Tassonomia della Qualità di McCall**, articolata sulle tre dimensioni fondamentali:

### 1. Product Operations (Operazioni sul Prodotto)
* **Correttezza (Correctness):** Il sistema soddisfa appieno i requisiti clinici funzionali specificati.  
  *Verifica:* Esecuzione automatizzata della suite di test JUnit 5 integrata nella build Maven (`mvn test`).
* **Affidabilità (Reliability):** Il software gestisce stati d'errore o input incongrui senza crash imprevisti.  
  *Verifica:* Incapsulamento delle anomalie in eccezioni di dominio specifiche (`InvalidPlanStateException`, `ResourceConflictException`) verificate con asserzioni `assertThrows`.
* **Efficienza (Efficiency):** Utilizzo ottimizzato delle risorse computazionali e tempi di risposta istantanei.  
  *Verifica:* Uso di un database relazionale embedded H2 in-memory/file locale leggero, privo di overhead di rete o processi esterni.
* **Integrità (Integrity):** Protezione dei dati clinici da modifiche non autorizzate o inconsistenti.  
  *Verifica:* Incapsulamento restrittivo (information hiding) e separazione dei privilegi tramite modello dei ruoli utente.
* **Usabilità (Usability):** Facilità di interazione e chiarezza nella gestione delle sedute e delle agende.  
  *Verifica:* Ispezione euristica dell'interfaccia utente e test di usabilità guidati con scenari tipici.

### 2. Product Revision (Revisione e Manutenibilità)
* **Manutenibilità (Maintainability):** Semplicità nel localizzare e correggere difetti o modificare regole di business.  
  *Verifica:* Rispetto del limite di complessità ciclomatica di McCabe ($CV \le 10$ per metodo) e rispetto del principio di singola responsabilità (SRP).
* **Testabilità (Testability):** Semplicità nell'isolare e collaudare le singole unità logiche.  
  *Verifica:* Monitoraggio della copertura del codice con soglia minima target $\ge 75\%$ di Line e Branch Coverage sui package `service` e `model`.
* **Flessibilità (Flexibility):** Capacità del sistema di accogliere nuovi requisiti o tipologie di trattamento senza stravolgimenti architetturali.  
  *Verifica:* Adozione di interfacce e pattern strutturali/comportamentali che garantiscono conformità all'Open/Closed Principle.

### 3. Product Transition (Transizione e Portabilità)
* **Portabilità (Portability):** Funzionamento trasparente e identico su diverse piattaforme operative (Windows, macOS, Linux).  
  *Verifica:* Utilizzo esclusivo di JDK standard 17+, build gestita interamente tramite `pom.xml` Maven, divieto assoluto di percorsi di file assoluti nel codice.
* **Riutilizzabilità (Reusability):** Possibilità di impiegare componenti di dominio (es. modulo di calcolo ROM, gestione ruoli) in progetti futuri o come base per la tesi di laurea.  
  *Verifica:* Basso accoppiamento intermodulare verificato tramite analisi delle dipendenze.
* **Interoperabilità (Interoperability):** Predisposizione del software a dialogare con strumenti esterni o database standard.  
  *Verifica:* Strutturazione dei dati conforme a dialetto standard SQL ANSI tramite H2.

---

## 10. Pacchetti di Lavoro (Work Breakdown Structure - WBS)

La scomposizione gerarchica delle attività del progetto assegna responsabilità chiare a ciascun componente:

* **WP1 – Project Management & Configuration Setup** *(Resp: Franchini, Mazzoleni)*
  * WP1.1: Stesura iniziale e revisioni progressive del Project Plan (Doc 1).
  * WP1.2: Inizializzazione repository GitHub, branch protection, `.gitignore` e Kanban Board.
  * WP1.3: Definizione convenzioni di codifica, template di issue e modello di branching.
* **WP2 – Requirements Engineering & Software Quality** *(Resp: Ivasco, Ghislanzoni)*
  * WP2.1: Elicitazione dei requisiti clinici e definizione del glossario di dominio.
  * WP2.2: Stesura documento di specifica requisiti software (Doc 3) secondo standard IEEE 830.
  * WP2.3: Definizione delle metriche di qualità del software e conformità al modello McCall.
* **WP3 – Modelling & System Design** *(Resp: Mazzoleni, Franchini)*
  * WP3.1: Modellazione dei Casi d'Uso (Use Case Diagram e Use Case Specifications).
  * WP3.2: Modellazione statica (Class Diagram di dominio) e generazione codice tramite StarUML/Javazzar.
  * WP3.3: Modellazione dinamica: State Machine Diagram (ciclo piano), Sequence e Activity Diagram.
  * WP3.4: Stesura Documento 4 (Architettura e Design secondo IEEE 1016, pattern e viste architetturali).
* **WP4 – Software Implementation** *(Resp: Tutti i componenti)*
  * WP4.1: *Database Layer:* Configurazione connessione H2 e implementazione repository DAO *(Ivasco)*.
  * WP4.2: *Core & Service Layer:* Logica del piano riabilitativo, controlli clinici e algoritmi di valutazione *(Franchini, Ghislanzoni)*.
  * WP4.3: *User Interface Layer:* Sviluppo interfacce utente e gestione input operatore *(Mazzoleni, Ghislanzoni)*.
  * WP4.4: *Integration:* Disaccoppiamento inter-package e wiring dei componenti *(Franchini, Mazzoleni)*.
* **WP5 – Verification, Testing & Static Analysis** *(Resp: Tutti i componenti)*
  * WP5.1: Progettazione e stesura della suite di test unitari JUnit 5 su DB, Service e UI.
  * WP5.2: Misurazione della copertura del codice (Code Coverage) e test sui casi limite.
  * WP5.3: Calcolo analitico della complessità ciclomatica di McCabe e verifica accoppiamento package.
  * WP5.4: Stesura Documento 5 (Testing e Validazione).
* **WP6 – Refactoring, Maintenance & Final Delivery** *(Resp: Franchini, Ghislanzoni, Mazzoleni, Ivasco)*
  * WP6.1: Ispezione e rimozione di eventuali code smell e antipattern (Doc 6).
  * WP6.2: Finalizzazione Documento 2 (Software Life Cycle, SCM, Team Organization).
  * WP6.3: Consolidamento relazione generale e congelamento release sul branch `main`.
  * WP6.4: Allestimento slide della presentazione orale e predisposizione ambiente per la demo live.

---

## 11. Risorse

* **Risorse Hardware:** 4 personal computer portatili utilizzati dai componenti del team per lo sviluppo e il collaudo locale.
* **Risorse Software & Strumenti:**
  * **Linguaggio & SDK:** Java Development Kit (JDK) versione 17+.
  * **IDE:** Eclipse IDE for Java Developers (versione 2024/2026).
  * **Build & Dependency Management:** Apache Maven 3.9+.
  * **DBMS:** H2 Database Engine (modalità embedded locale, zero installazioni esterne).
  * **Modellazione UML & Code Gen:** StarUML v5 con estensione Javazzar.
  * **Collaboration & Tracking:** GitHub (Git repository, Pull Requests, Issues, Projects Kanban), Discord per meeting di allineamento e screen-sharing.

---

## 12. Budget e Programma Temporale

* **Budget Totale:** Circa **160 ore-uomo** complessive ripartite equamente tra i 4 componenti (~40 ore pro capite). Nessun budget economico monetario richiesto (utilizzo esclusivo di tool e librerie open-source/gratuite).
* **Cronoprogramma delle Milestone:**
  * **Settimana 1-2 (Sprint 1 - Fine Ottobre):** Conclusione WP1 e avvio WP2. Consegna e sottomissione del Project Plan iniziale (Doc 1) per approvazione del docente tramite GitHub Issue $\rightarrow$ **Milestone M1**.
  * **Settimana 3-4 (Sprint 2 - Metà Novembre):** Conclusione WP2 (Doc 3 IEEE 830) e WP3 (Modellazione StarUML, Class & State Diagram, prima generazione codice Java) $\rightarrow$ **Milestone M2**.
  * **Settimana 5-6 (Sprint 3 - Inizio Dicembre):** Avanzamento WP4 (Sviluppo Core, Service, H2 DB, UI) e WP5 (Prime classi di test JUnit 5) $\rightarrow$ **Milestone M3**.
  * **Settimana 7-8 (Sprint 4 - Fine Dicembre/Pre-esame):** Conclusione WP5 (Coverage e calcolo metriche McCabe), WP6 (Doc 2, Doc 5, Doc 6, refactoring).  

---

## 13. Gestione dei Cambiamenti (Change Management)

* Qualsiasi modifica strutturale ai requisiti, all'architettura o alla pianificazione originaria deve essere formalizzata mediante l'apertura di una **GitHub Issue** etichettata con il tag `change-request`.
* Le richieste di modifica vengono analizzate collegialmente durante gli stand-up meeting per valutarne l'impatto tecnico, le dipendenze e l'aggravio in ore-uomo.
* In caso di approvazione, la modifica viene recepita nella documentazione tecnica e tracciata con incremento del numero di versione nella tabella del **Registro delle Modifiche** posta all'inizio di ciascun documento.

---

## 14. Consegna

* **Condivisione Repository:** Repository GitHub condiviso con accesso collaboratore concesso ai docenti (`garganti`, `silviabonfanti`).
* **Artefatti di Consegna (Deliverables):**
  1. Cartella `codice/medicalhub`: Progetto completo Java/Maven importabile ed eseguibile su Eclipse senza errori di compilazione, con tutti i test JUnit 5 passanti (`mvn test` con esito positivo).
  2. Cartella `documenti/`: Documentazione tecnica completa suddivisa nei 6 documenti in formato Markdown/PDF conformi alle linee guida.
  3. Cartella `documenti/models/`: File di progetto sorgente `.mdj` di StarUML contenente tutti i diagrammi realizzati.
* **Presentazione Orale:**
  * Esposizione tramite slide (utilizzando il template ufficiale fornito dai docenti) della durata massima di **35 minuti per team da 4 componenti** (+ circa 10 minuti dedicati a domande e osservazioni della commissione).
  * Esecuzione di una demo live su personal computer per mostrare l'esecuzione dell'applicazione e della suite di test unitari.