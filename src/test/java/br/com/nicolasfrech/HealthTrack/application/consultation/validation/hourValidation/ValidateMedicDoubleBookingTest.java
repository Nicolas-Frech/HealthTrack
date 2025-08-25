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

public class ValidateMedicDoubleBookingTest {

    private ConsultationRepository consultationRepository;
    private ValidateMedicDoubleBooking validate;

    @BeforeEach
    public void setup() {
        consultationRepository = mock(ConsultationRepository.class);
        validate = new ValidateMedicDoubleBooking(consultationRepository);
    }

    @Test
    @DisplayName("Should pass when doctor has no other consultation at the same time")
    public void doctorAvailable() {
        Consultation consultation = new Consultation(1L, 1L, LocalDateTime.of(2025, 8, 25, 10, 0));

        when(consultationRepository.existsByMedicIdAndDateAndStatus(
                consultation.getMedicId(),
                consultation.getDate(),
                ConsultationStatus.SCHEDULED
        )).thenReturn(false);

        assertDoesNotThrow(() -> validate.validate(consultation, consultation.getDate()));
    }

    @Test
    @DisplayName("Should throw ValidateException when doctor already has a consultation at the same time")
    public void doctorDoubleBooked() {
        Consultation consultation = new Consultation(1L, 1L, LocalDateTime.of(2025, 8, 25, 10, 0));

        when(consultationRepository.existsByMedicIdAndDateAndStatus(
                consultation.getMedicId(),
                consultation.getDate(),
                ConsultationStatus.SCHEDULED
        )).thenReturn(true);

        assertThrows(ValidateException.class, () -> validate.validate(consultation, consultation.getDate()));
    }

}
