package br.com.nicolasfrech.HealthTrack.application.consultation.validation.bookConsultation;

import br.com.nicolasfrech.HealthTrack.application.consultation.dto.BookConsultationDTO;
import br.com.nicolasfrech.HealthTrack.infra.exception.ValidateException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class ValidateAdvanceNoticeTest {

    private ValidateAdvanceNotice validateAdvanceNotice = new ValidateAdvanceNotice();

    @Test
    @DisplayName("Should book consultation with 1 hour advance notice")
    public void validateAdvanceNoticeScenario01() {
        BookConsultationDTO dto = new BookConsultationDTO("SC123456", "123.456.789-00",
                LocalDateTime.now().plusDays(1).withHour(14).withMinute(30));

        assertDoesNotThrow(() -> validateAdvanceNotice.validate(dto));
    }

    @Test
    @DisplayName("Should NOT book consultation without 1 hour advance notice")
    public void validateAdvanceNoticeScenario02() {
        BookConsultationDTO dto = new BookConsultationDTO("SC123456", "123.456.789-00",
                LocalDateTime.now());

        assertThrows(ValidateException.class, () -> validateAdvanceNotice.validate(dto));
    }

}
