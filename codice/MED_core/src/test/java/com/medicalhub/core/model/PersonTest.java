package com.medicalhub.core.model;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.medicalhub.core.model.role.DoctorRole;
import com.medicalhub.core.model.role.PatientRole;

class PersonTest {

    private Person mario;

    @BeforeEach
    void setUp() {
        mario = new Person("RSSMRA80A01A794X", "Mario", "Rossi", LocalDate.of(1980, 1, 1));
    }

    @Test
    void testCostruttoreValidaCodiceFiscale() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Person("   ", "Luigi", "Verdi", LocalDate.of(1990, 1, 1));
        });
        assertEquals("Il codice fiscale è obbligatorio", exception.getMessage());
        
        Person luigi = new Person("vRdlgi90", "Luigi", "Verdi", LocalDate.of(1990, 1, 1));
        assertEquals("VRDLGI90", luigi.getTaxCode());
    }

    @Test
    void testAggiuntaERecuperoRuolo() {
    	
        PatientRole patientRole = new PatientRole("Calcio", "Nessuno");
        mario.addRole(patientRole);
        
        assertTrue(mario.hasRole(PatientRole.class), "Mario dovrebbe essere un paziente");
        
        PatientRole retrieved = mario.getRole(PatientRole.class);
        assertNotNull(retrieved);
        assertEquals("Calcio", retrieved.getPracticedSport());
        
        assertFalse(mario.hasRole(DoctorRole.class), "Mario NON dovrebbe essere un medico");
        assertNull(mario.getRole(DoctorRole.class), "Il ruolo medico deve essere null");
    }

    @Test
    void testSostituzioneRuoloAutomatico() {
    	
        mario.addRole(new PatientRole("Calcio", "Nessun infortunio"));
        
        mario.addRole(new PatientRole("Tennis", "Distorsione caviglia"));
        
        assertEquals(1, mario.getRoles().size(), "Ci deve essere un solo ruolo nella mappa");
        
        PatientRole ruoloAttuale = mario.getRole(PatientRole.class);
        assertEquals("Tennis", ruoloAttuale.getPracticedSport(), "Il ruolo deve essere stato sovrascritto con quello nuovo");
    }

    @Test
    void testRimozioneRuolo() {
        mario.addRole(new DoctorRole("BG-123", "Ortopedia"));
        assertTrue(mario.hasRole(DoctorRole.class));
        
        mario.removeRole(DoctorRole.class);
        
        assertFalse(mario.hasRole(DoctorRole.class), "Il ruolo da medico deve essere stato rimosso");
        assertEquals(0, mario.getRoles().size());
    }
}