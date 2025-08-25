package br.com.nicolasfrech.HealthTrack.application.consultation.validation.hourValidation;

import br.com.nicolasfrech.HealthTrack.domain.consultation.Consultation;
import br.com.nicolasfrech.HealthTrack.infra.exception.ValidateException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ValidateHourTest {

    private ValidateHour validateHour = new ValidateHour();

    @Test
    @DisplayName("Should pass for consultation within allowed hours")
    public void consultationWithinHours() {
        Consultation consultation = new Consultation(1L, 1L,
                LocalDateTime.of(2025, Month.AUGUST, 25, 10, 0));

        assertDoesNotThrow(() -> validateHour.validate(consultation, consultation.getDate()));
    }

    @Test
    @DisplayName("Should fail for consultation before 8 AM")
    public void consultationBeforeOpening() {
        Consultation consultation = new Consultation(1L, 1L,
                LocalDateTime.of(2025, Month.AUGUST, 25, 7, 0));

        assertThrows(ValidateException.class, () -> validateHour.validate(consultation, consultation.getDate()));
    }

    @Test
    @DisplayName("Should fail for consultation after 6 PM")
    public void consultationAfterClosing() {
        Consultation consultation = new Consultation(1L, 1L,
                LocalDateTime.of(2025, Month.AUGUST, 25, 19, 0));

        assertThrows(ValidateException.class, () -> validateHour.validate(consultation, consultation.getDate()));
    }

    @Test
    @DisplayName("Should fail for consultation on Sunday")
    public void consultationOnSunday() {
        Consultation consultation = new Consultation(1L, 1L,
                LocalDateTime.of(2025, Month.AUGUST, 24, 10, 0));

        assertThrows(ValidateException.class, () -> validateHour.validate(consultation, consultation.getDate()));
    }
}
