package br.com.nicolasfrech.HealthTrack.application.consultation;

import br.com.nicolasfrech.HealthTrack.application.consultation.dto.BookConsultationDTO;
import br.com.nicolasfrech.HealthTrack.application.consultation.validation.bookConsultation.ValidateHour;
import br.com.nicolasfrech.HealthTrack.infra.exception.ValidateException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ValidateHourTest {

    private ValidateHour validateHour = new ValidateHour();

    @Test
    @DisplayName("Should book consultation inside allowed hours")
    public void validateHourScenario01() {
        BookConsultationDTO dto = new BookConsultationDTO("SC123456", "123.456.789-00",
                LocalDateTime.now().plusDays(1).withHour(14).withMinute(30));

        assertDoesNotThrow(() -> validateHour.validate(dto));
    }

    @Test
    @DisplayName("Should NOT book consultation outside allowed hours")
    public void validateHourScenario02() {
        BookConsultationDTO dtoMorning = new BookConsultationDTO(
                "SC123456",
                "123.456.789-00",
                LocalDateTime.now().plusDays(1).withHour(7).withMinute(30)
        );

        assertThrows(ValidateException.class, () -> validateHour.validate(dtoMorning));

        BookConsultationDTO dtoEvening = new BookConsultationDTO(
                "SC123456",
                "123.456.789-00",
                LocalDateTime.now().plusDays(1).withHour(19).withMinute(0)
        );

        assertThrows(ValidateException.class, () -> validateHour.validate(dtoEvening));
    }
}
