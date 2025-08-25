package br.com.nicolasfrech.HealthTrack.application.consultation.validation.hourValidation;

import br.com.nicolasfrech.HealthTrack.application.consultation.gateway.ConsultationRepository;
import br.com.nicolasfrech.HealthTrack.domain.consultation.Consultation;
import br.com.nicolasfrech.HealthTrack.domain.consultation.ConsultationStatus;
import br.com.nicolasfrech.HealthTrack.infra.exception.ValidateException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ValidatePatientDoubleBookingTest {

    private ConsultationRepository consultationRepository;
    private ValidatePatientDoubleBooking validate;

    @BeforeEach
    public void setup() {
        consultationRepository = mock(ConsultationRepository.class);
        validate = new ValidatePatientDoubleBooking(consultationRepository);
    }

    @Test
    @DisplayName("Should pass when patient has no other consultation at the same time")
    public void patientAvailable() {
        Consultation consultation = new Consultation(1L, 1L, LocalDateTime.of(2025, 8, 25, 10, 0));

        when(consultationRepository.existsByPatientIdAndDateAndStatus(
                consultation.getPatientId(),
                consultation.getDate(),
                ConsultationStatus.SCHEDULED
        )).thenReturn(false);

        assertDoesNotThrow(() -> validate.validate(consultation, consultation.getDate()));
    }

    @Test
    @DisplayName("Should fail when patient already has a consultation at the same time")
    public void patientDoubleBooked() {
        Consultation consultation = new Consultation(1L, 1L, LocalDateTime.of(2025, 8, 25, 10, 0));

        when(consultationRepository.existsByPatientIdAndDateAndStatus(
                consultation.getPatientId(),
                consultation.getDate(),
                ConsultationStatus.SCHEDULED
        )).thenReturn(true);

        assertThrows(ValidateException.class, () -> validate.validate(consultation, consultation.getDate()));
    }
}
