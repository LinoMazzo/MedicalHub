## PROJECT PLAN

Progetto: MedicalHub

Corso: Ingegneria del Software (AA 2026/27)

Docenti: Prof. Angelo Gargantini, Dott.ssa Silvia Bonfanti

Team di Sviluppo (4 componenti): Franchini Mattia 1099581, Ghislanzoni Fabio 1099423,

Mazzoleni Lino 1098962, Ivasco Riccardo 1101140

### Storico delle Versioni
| Versione | Data | Autori | Descrizione delle modifiche |
| :--- | :--- | :--- | :--- |
| **1.0.0** | 01/10/2026 | Team MedicalHub | Versione iniziale del Project Plan |
| **1.0.1** | 03/10/2026 | Franchini Mattia | Aggiunta Membro del gruppo e aggiornamento budg/temp |

## 1. Introduzione

- Background & Motivazione: La riabilitazione post-operatoria e sportiva richiede una gestione coordinata tra medici, fisioterapisti e pazienti, spesso ostacolata da strumenti cartacei e frammentari.

- Obiettivo: Realizzare un sistema software (Medical Hub) per digitalizzare la gestione del piano riabilitativo: dalla diagnosi iniziale, alla prenotazione delle sedute vincolate a sale/ macchinari, fino al monitoraggio oggettivo dei progressi e al rilascio dell'idoneità sportiva.

- Risultati attesi:

- 1. Documentazione completa del progetto

- 2. Progetto Java sviluppato su Eclipse con gestione dipendenze via Maven.

- 3. Suite di casi di test JUnit con report di copertura.

- 4. Repository GitHub strutturata con storico tracciabile di issue, branch e pull request.

## 2. Modello di Processo

- Modello adottato: Modello Agile basato su Scrum / Iterativo-Incrementale a Timebox.

- Articolazione in Sprint: Il progetto è suddiviso in n Sprint da 2 settimane ciascuno:

- Sprint 1 (Milestone M1): Analisi Requisiti e Use Case Diagram in StarUML. Approvazione Project Plan.

- Criteri di avanzamento: Ogni milestone è considerata raggiunta solo dopo verifica collegiale e chiusura delle relative Issue su GitHub.

## 3. Organizzazione del Progetto

- Relazione con gli Stakeholder: Il team modella il sistema basandosi su scenari clinici reali di fisioterapia post-operatoria.

- Ruoli nel Team (4 componenti):

- Mazzoleni Lino ()

- Franchini Mattia ()

- Ghislanzoni Fabio ()

- Ivasco Riccardo ()

(Nota: tutti e tre i membri parteciperanno comunque attivamente alla scrittura del codice Java e alle code review).

## 4. Standard, Linee Guida, Procedure

- Standard di Codifica: Oracle Java Code Conventions (naming convention camelCase, corretta visibilità dei campi).

- Specifiche Requisiti:

- Modellazione: Standard UML 2.x realizzato tramite StarUML v5.

- Controllo Versione: Branching model Feature-Branch Workflow:

- Branch main: solo codice stabile e testato.

- Branch develop: ramo di integrazione.


- Branch feature/<nome-feature>: rami di sviluppo individuali uniti solo tramite Pull Request approvata da almeno un altro membro del team.

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

## 7. Personale
s
Questa sezione definisce il dimensionamento quantitativo del team, la variazione temporale dello sforzo lungo le fasi di sviluppo e il piano di formazione tecnica necessario a garantire l'allineamento delle competenze.

#### 7.1 Dimensionamento e Impegno Complessivo
* **Organico:** 4 componenti (i cui ruoli e responsabilità specialistiche sono formalizzati nella Sezione 3).
* **Impegno Individuale:** Circa **35-40 ore a persona**, per un totale aggregato di circa **150-160 ore-uomo**.
* **Distribuzione Temporale:** L'attività si sviluppa su un arco di **8 settimane**, con un carico medio individuale di circa **4.5 - 5 ore/settimana**.

#### 7.2 Distribuzione dello Sforzo per Fasi di Sviluppo
In conformità alla teoria di gestione del software, la quantità e la tipologia di sforzo richiesto variano significativamente nelle diverse fasi del ciclo di vita:

1. **Fase Iniziale (Settimane 1–2):** Picco di attività su requisiti e analisi di dominio. Impegno elevato per la redazione del *Project Plan*, la stesura dei requisiti IEEE 830, la prima modellazione dei casi d'uso in StarUML e l'impostazione dell'infrastruttura iniziale (repo GitHub e struttura multi-modulo Maven).
2. **Fase Centrale (Settimane 3–5):** Picco di attività implementativa e architetturale. Modellazione dei diagrammi statici e dinamici (Class Diagram, State Machine, Sequence), sviluppo del codice sorgente Java nei rispettivi moduli e implementazione del database embedded SQLite.
3. **Fase Finale (Settimane 6–8):** Picco di attività di collaudo, assicurazione qualità e documentazione. Scrittura estensiva della suite di test JUnit 5, verifica della code coverage, analisi statica delle metriche (McCabe, metriche di pacchetto di Martin), refactoring e redazione dei documenti finali.

| Macro-Attività | Settimane 1-2 (Requisiti & Plan) | Settimane 3-5 (Design & Sviluppo) | Settimane 6-8 (Testing & Delivery) | Totale Ore-Uomo |
| :--- | :---: | :---: | :---: | :---: |
| **Ingegneria dei Requisiti & Management** | 24 h | 8 h | 6 h | **38 h** |
| **Architettura Software & Modellazione UML** | 12 h | 20 h | 6 h | **38 h** |
| **Sviluppo Moduli Java & Database SQLite** | 6 h | 24 h | 8 h | **38 h** |
| **Testing JUnit 5, Metriche & QA** | 6 h | 10 h | 22 h | **38 h** |
| **Totale Ore per Fase** | **48 h** | **62 h** | **42 h** | **152 h** |

#### 7.3 Piano di Formazione e Allineamento Tecnico (Training Plan)
Per garantire che tutti i membri del gruppo posseggano le competenze necessarie a ricoprire i propri compiti e a eseguire efficaci *code review*, sono state pianificate sessioni di studio individuale e congiunto nelle prime due settimane:
* **Java 17 Avanzato:** Studio e ripasso delle funzionalità orientate agli oggetti avanzate.
* **Database Embedded SQLite & JDBC:** Studio della libreria `sqlite-jdbc`, sintassi SQL, configurazione dei percorsi relativi (per garantire la portabilità del file `.db` senza percorsi assoluti) e implementazione del pattern Singleton per la gestione della connessione.
* **Testing e Asserzioni con JUnit 5:** Approfondimento del ciclo di vita dei test, testing delle eccezioni e analisi della copertura del codice.
* **Strumenti di Progettazione e Metriche:** Apprendimento dell'uso di **StarUML v5** (modellazione UML 2.x e generazione scheletri Java) e studio delle metriche di modularità e complessità richieste dal corso (complessità ciclomatica di McCabe e metriche di accoppiamento/instabilità di Martin con StanIDE/JDepend).

#### 7.4 Gestione del Carico e Continuità Operativa
* **Bilanciamento del Lavoro:** Il carico viene monitorato settimanalmente tramite le metriche della Kanban Board di GitHub per evitare sovraccarichi individuali.
* **Rotazione e Supporto (Pairing/Backup):** Nessun modulo è a conoscenza esclusiva di un singolo sviluppatore; l'obbligo di revisione formale delle Pull Request da parte di componenti diversi garantisce la condivisione della conoscenza e la continuità del progetto in caso di indisponibilità temporanea di un membro.

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

## 9. Garanzia di Qualità

- Code Review: Nessun commit diretto su main; ogni Pull Request richiede la review e l'approvazione di un compagno di gruppo.

## 10. Pacchetti di Lavoro

## 11. Risorse

- Hardware: 4 computer personali per sviluppo e test.

- Software & Tool:

- IDE: Eclipse 2026.

- Build Tool: Apache Maven 3.9+.

- UML Tool: StarUML v5.

- Comunicazione & Board: GitHub, Discord.

## 12. Budget e Programma Temporale

- Budget Totale: ~160 ore-uomo complessive (zero costi finanziari, uso esclusivo di software open source/gratuito).

- Cronoprogramma Milestone:

- Settimana 1: WP1 (Project Plan). →Invio Project Plan per approvazione.


## 13. Gestione dei Cambiamenti

- Ogni proposta di modifica ai requisiti o all'architettura deve essere aperta come GitHub Issue etichettata con change-request.

- La modifica viene discussa collegialmente; se approvata, si aggiorna la documentazione mantenendo lo storico delle versioni nel registro delle modifiche (Change Log) in testa al documento.

## 14. Consegna

- Condivisione Repository: Repo GitHub condivisa con i docenti (garganti, silviabonfanti). Contenuto della Consegna:

- 1. Cartella codice/ con il progetto Eclipse Maven. 2. Cartella documenti/ con la relazione completa in markdown suddivisa nei capitoli richiesti.

- 3. File .mdj del progetto StarUML contenente tutti i diagrammi sorgente.

- Presentazione: Slide (max 30 minuti di esposizione per 3 persone) basate sul template del corso + demo live su PC all'esame orale.
