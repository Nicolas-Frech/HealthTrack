package br.com.nicolasfrech.HealthTrack.application.patient;

import br.com.nicolasfrech.HealthTrack.application.patient.dto.PatientRegistDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class PatientServiceTest {

    @Autowired
    private PatientService patientService;

    @Test
    @DisplayName("Should register valid Patient")
    void registPatientCenary01() {
        PatientRegistDTO dto = new PatientRegistDTO("name","123.456.789-00", 18, "email@email.com", "(48) 99456-3021");

        assertDoesNotThrow(() -> patientService.registPatient(dto));
    }

    @Test
    @DisplayName("Should not register invalid cpf")
    void registPatientCenary02() {
        PatientRegistDTO dto = new PatientRegistDTO("name","12456.789-00", 18, "email@email.com", "(48) 99456-3021");

        assertThrows(IllegalArgumentException.class, () -> patientService.registPatient(dto));
    }

    @Test
    @DisplayName("Should not register invalid email")
    void registPatientCenary03() {
        PatientRegistDTO dto = new PatientRegistDTO("name","124.556.789-00", 18, "emailemail.com", "(48) 99456-3021");

        assertThrows(IllegalArgumentException.class, () -> patientService.registPatient(dto));
    }

    @Test
    @DisplayName("Should not register invalid telephone")
    void registPatientCenary04() {
        PatientRegistDTO dto = new PatientRegistDTO("name","124.556.789-00", 18, "emailemail.com", "946-3021");

        assertThrows(IllegalArgumentException.class, () -> patientService.registPatient(dto));
    }
}
