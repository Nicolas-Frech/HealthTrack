package br.com.nicolasfrech.HealthTrack.application.consultation.validation.updateStatus;


import br.com.nicolasfrech.HealthTrack.application.consultation.dto.UpdateStatusDTO;
import br.com.nicolasfrech.HealthTrack.domain.consultation.Consultation;
import br.com.nicolasfrech.HealthTrack.domain.consultation.ConsultationStatus;
import br.com.nicolasfrech.HealthTrack.domain.medic.Medic;
import br.com.nicolasfrech.HealthTrack.domain.medic.Speciality;
import br.com.nicolasfrech.HealthTrack.domain.patient.Patient;
import br.com.nicolasfrech.HealthTrack.infra.exception.ValidateException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ValidateCancelStatusTest {

    private ValidateCancelStatus validateCancelStatus = new ValidateCancelStatus();

    private Medic medic;
    private Patient patient;

    @BeforeEach
    void setUp() {
        medic = new Medic(
                "Medic",
                "SC123456",
                Speciality.CARDIOLOGIA,
                "(47) 99999-4949",
                "email@email.com"
        );

        patient = new Patient(
                "Patient",
                "123.456.789-00",
                12,
                "email@email.com",
                "(47) 99999-5948"
        );
    }

    @Test
    @DisplayName("Should cancel consultation with 24 hours advance notice")
    public void cancelStatusScenario01() {
        Consultation consultation = new Consultation(medic, patient, LocalDateTime.now().plusHours(25));
        UpdateStatusDTO dto = new UpdateStatusDTO(ConsultationStatus.CANCELED);

        assertDoesNotThrow(() -> validateCancelStatus.validate(consultation, dto));
    }

    @Test
    @DisplayName("Should cancel consultation with 24 hours advance notice")
    public void cancelStatusScenario02() {
        Consultation consultation = new Consultation(medic, patient, LocalDateTime.now().plusHours(23));
        UpdateStatusDTO dto = new UpdateStatusDTO(ConsultationStatus.CANCELED);

        assertThrows(ValidateException.class, () -> validateCancelStatus.validate(consultation, dto));
    }
}
