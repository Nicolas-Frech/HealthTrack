package br.com.nicolasfrech.HealthTrack.application.patient;
import br.com.nicolasfrech.HealthTrack.application.patient.dto.PatientUpdateDTO;
import br.com.nicolasfrech.HealthTrack.application.patient.gateway.PatientRepository;
import br.com.nicolasfrech.HealthTrack.domain.patient.Patient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class PatientServiceUpdateTest {


    private PatientService patientService;
    private PatientRepository patientRepository;

    @BeforeEach
    void setUp() {
        patientRepository = Mockito.mock(PatientRepository.class);
        patientService = new PatientService(patientRepository);
    }

    @Test
    @DisplayName("Should update valid Patient")
    void updatePatientCenary01() {
        // Arrange
        Patient patient = new Patient("John Doe", "123.456.789-00", 30, "john@email.com", "(48)99999-9999");

        when(patientRepository.findById(1L)).thenReturn(patient);
        when(patientRepository.save(any(Patient.class))).thenReturn(patient);

        PatientUpdateDTO dto = new PatientUpdateDTO(1L, "new@email.com", 31, "(48)98888-8888");

        // Act & Assert
        assertDoesNotThrow(() -> patientService.updatePatient(dto));
        assertEquals("new@email.com", patient.getEmail());
        assertEquals(31, patient.getAge());
        assertEquals("(48)98888-8888", patient.getTelephone());
    }

    @Test
    @DisplayName("Should not update invalid patient")
    void updatePatientNotFoundShouldThrow() {
        //Arrange
        when(patientRepository.findById(999L))
                .thenThrow(new IllegalArgumentException("Patient not found"));

        PatientUpdateDTO dto = new PatientUpdateDTO(999L, "new@email.com", 31, "(48)98888-8888");

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> patientService.updatePatient(dto));

        assertEquals("Patient not found", exception.getMessage());
    }
}
