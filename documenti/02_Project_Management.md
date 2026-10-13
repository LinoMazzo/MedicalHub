# DOCUMENTO 2: GESTIONE DEL PROGETTO

**Progetto:** MedicalHub  
**Corso:** Ingegneria del Software (AA 2026/27)  
**Team:** Franchini Mattia, Ghislanzoni Fabio, Mazzoleni Lino, Ivasco Riccardo  

---

## 2.1 Software Life Cycle

Come stabilito nel *Project Plan*, il team adotta un modello di processo di tipo **Agile iterativo e incrementale**, fortemente ispirato alle dinamiche di Scrum. Questo approccio ci permette di gestire i requisiti in evoluzione e di avere versioni funzionanti del software al termine di ogni iterazione.

### Approccio MDA (Model-Driven Architecture)
In linea con le direttive del corso, lo sviluppo non avverrà scrivendo tutto il codice da zero, ma adottando un approccio **MDA**. Nello specifico, le strutture base delle classi di dominio in Java (gli scheletri, gli attributi e le firme dei metodi principali) verranno generate automaticamente a partire dal *Class Diagram* realizzato su **StarUML**. Successivamente, il codice autogenerato verrà importato nell'IDE Eclipse, dove il team potrà procedere con l'implementazione.

### Organizzazione in Sprint (Timebox)
Lo sviluppo effettivo è suddiviso in Sprint (Timebox). Ogni Sprint è pianificato selezionando i task dal Product Backlog (gestita su GitHub) e verrà concluso con una verifica degli obiettivi raggiunti.

*   **Sprint 1 (01/10/2026 – 16/10/2026):** Redazione iniziale del project plan e del documento di gestione del progetto. Prima elicitazione e specifica dei requisiti tramite analisi degli scenari.
*   **Sprint 2 (17/10/2026 – 31/10/2026):** [...].

**Scostamenti dal piano originale:**
[...].

---

## 2.2 Configuration Management

La gestione delle configurazioni, del versionamento e della collaborazione avviene interamente tramite **Git** e **GitHub** per garantire la consistenza del codice sviluppato in contemporanea dal team.

### Organizzazione del Workspace
La repository è stata strutturata separando nettamente i documenti dal codice, come richiesto dalle specifiche di progetto:
*   `/documenti`: Contiene i file Markdown della relazione finale e il file `.mdj` contenente i modelli StarUML.
*   `/codice`: Contiene i progetti sviluppati su IDE Eclipse. I progetti sono stati configurati tramite **Maven** per la gestione delle dipendenze esterne.
*   **Gestione dei file ignorati:** È stato configurato un file `.gitignore` per escludere i file generati dalla compilazione (come i `.class` e la cartella `/target` di Maven).

### Versioning e Feature-Branch Workflow
Per evitare conflitti, il team ha evitato commit diretti sul ramo principale, adottando un **Feature-Branch Workflow**.

Il team ha adottato un Feature-Branch Workflow basato su due rami principali e rami di funzionalità temporanei:
1.  **Branch `main`:** Riservato esclusivamente al codice testato e approvato.
2.  **Branch develop:** Ramo principale di integrazione continua dove confluiscono le funzionalità completate.
3.  **Branch feature/:** Ramo dedicato per ogni singolo task o documento.
4.  **Pull Request (PR) e Code Review:** Terminata una feature, è stata aperta una PR verso develop. Il merge è avvenuto solo previa approvazione (review) di almeno un altro componente del team.

### Task Tracking 
L'assegnazione e il tracciamento dei compiti sono gestiti tramite una **Kanban Board** su GitHub, spostando i task attraverso le colonne di avanzamento.

---

## 2.3 People Management and Team Organization

Essendo un team composto da 4 persone, è stato necessario definire un'organizzazione chiara per evitare sovrapposizioni. Il team ha operato in modo "Self-Organizing", ma con assegnazione di responsabilità specifiche per ottimizzare il lavoro:

*   **Mazzoleni Lino:** [...].
*   **Franchini Mattia:** [...].
*   **Ghislanzoni Fabio:** [...].
*   **Ivasco Riccardo:** [...].

**Modalità di Coordinamento:**
Il coordinamento del team avviene principalmente tramite **incontri di lavoro dal vivo (in presenza)**. Durante queste sessioni congiunte, il team discute l'avanzamento del progetto, progetta l'architettura in modo collaborativo e mantiene costantemente aggiornata la Kanban Board su GitHub. 

A integrazione degli incontri fisici, per garantire continuità durante le fasi di sviluppo individuale, saranno utilizzate frequentemente **videochiamate** per rapidi allineamenti (status update), code review condivise tramite screen-sharing e risoluzione tempestiva di eventuali bug bloccanti. Questa organizzazione garantirà un avanzamento costante e un apporto qualitativo e quantitativo equo da parte di tutti i membri del gruppo.