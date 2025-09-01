package br.com.nicolasfrech.HealthTrack.application.consultation.validation.hourValidation;

import br.com.nicolasfrech.HealthTrack.domain.consultation.Consultation;
import br.com.nicolasfrech.HealthTrack.domain.medic.Medic;
import br.com.nicolasfrech.HealthTrack.domain.medic.Speciality;
import br.com.nicolasfrech.HealthTrack.domain.patient.Patient;
import br.com.nicolasfrech.HealthTrack.infra.exception.ValidateException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ValidateHourTest {

    private ValidateHour validateHour = new ValidateHour();

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
    @DisplayName("Should pass for consultation within allowed hours")
    public void consultationWithinHours() {
        Consultation consultation = new Consultation(medic, patient,
                LocalDateTime.of(2025, Month.AUGUST, 25, 10, 0));

        assertDoesNotThrow(() -> validateHour.validate(consultation, consultation.getDate()));
    }

    @Test
    @DisplayName("Should fail for consultation before 8 AM")
    public void consultationBeforeOpening() {
        Consultation consultation = new Consultation(medic, patient,
                LocalDateTime.of(2025, Month.AUGUST, 25, 7, 0));

        assertThrows(ValidateException.class, () -> validateHour.validate(consultation, consultation.getDate()));
    }

    @Test
    @DisplayName("Should fail for consultation after 6 PM")
    public void consultationAfterClosing() {
        Consultation consultation = new Consultation(medic, patient,
                LocalDateTime.of(2025, Month.AUGUST, 25, 19, 0));

        assertThrows(ValidateException.class, () -> validateHour.validate(consultation, consultation.getDate()));
    }

    @Test
    @DisplayName("Should fail for consultation on Sunday")
    public void consultationOnSunday() {
        Consultation consultation = new Consultation(medic, patient,
                LocalDateTime.of(2025, Month.AUGUST, 24, 10, 0));

        assertThrows(ValidateException.class, () -> validateHour.validate(consultation, consultation.getDate()));
    }
}
