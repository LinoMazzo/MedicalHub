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

- Tracciamento Avanzamento: Utilizzo della GitHub Project Board (Kanban) con colonne: To Do, In Progress, In Review, Done.

- Comunicazione Interna: Brevi meeting bisettimanali (online via Discord) per monitorare lo stato di avanzamento e risolvere problematiche importanti.

- Gestione Priorità: Tecnica MoSCoW (Must have, Should have, Could have, Won't have).

## 6. Gestione dei Rischi

## 7. Personale

- Dimensionamento: 4 persone.

- Impegno individuale: Circa 35-40 ore a persona, per un totale di circa 150-160 ore-uomo distribuite sull'arco di 6-8 settimane.

- Formazione richiesta: Studio di JUnit, dei Design Pattern e delle funzionalità avanzate di Java.

## 8. Metodi e Tecniche

Analisi & Design: ingegneria dei requisiti formale.

• Modellazione: StarUML per Class Diagram, State Machine, Sequence Diagram, Activity

Diagram, Component e Package Diagram.

- Sviluppo: Java 17/21 su IDE Eclipse

• Testing: testing con JUnit 5.

CI/CD & Versioning: Git & GitHub per version control e issue tracking.

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
