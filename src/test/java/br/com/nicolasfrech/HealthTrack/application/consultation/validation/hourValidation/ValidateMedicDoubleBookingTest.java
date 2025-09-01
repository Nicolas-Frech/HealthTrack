package br.com.nicolasfrech.HealthTrack.application.consultation.validation.hourValidation;

import br.com.nicolasfrech.HealthTrack.application.consultation.gateway.ConsultationRepository;
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
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ValidateMedicDoubleBookingTest {

    private ConsultationRepository consultationRepository;
    private ValidateMedicDoubleBooking validate;

    private Medic medic;
    private Patient patient;

    @BeforeEach
    public void setup() {
        consultationRepository = mock(ConsultationRepository.class);
        validate = new ValidateMedicDoubleBooking(consultationRepository);

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
    @DisplayName("Should pass when doctor has no other consultation at the same time")
    public void doctorAvailable() {
        Consultation consultation = new Consultation(medic, patient, LocalDateTime.of(2025, 8, 25, 10, 0));

        when(consultationRepository.existsByMedicIdAndDateAndStatus(
                consultation.getMedic().getId(),
                consultation.getDate(),
                ConsultationStatus.SCHEDULED
        )).thenReturn(false);

        assertDoesNotThrow(() -> validate.validate(consultation, consultation.getDate()));
    }

    @Test
    @DisplayName("Should fail when doctor already has a consultation at the same time")
    public void doctorDoubleBooked() {
        Consultation consultation = new Consultation(medic, patient, LocalDateTime.of(2025, 8, 25, 10, 0));

        when(consultationRepository.existsByMedicIdAndDateAndStatus(
                consultation.getMedic().getId(),
                consultation.getDate(),
                ConsultationStatus.SCHEDULED
        )).thenReturn(true);

        assertThrows(ValidateException.class, () -> validate.validate(consultation, consultation.getDate()));
    }

}
