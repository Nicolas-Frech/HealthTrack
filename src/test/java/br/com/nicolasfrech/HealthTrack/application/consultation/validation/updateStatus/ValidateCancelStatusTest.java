package br.com.nicolasfrech.HealthTrack.application.consultation.validation.updateStatus;


import br.com.nicolasfrech.HealthTrack.application.consultation.dto.UpdateStatusDTO;
import br.com.nicolasfrech.HealthTrack.domain.consultation.Consultation;
import br.com.nicolasfrech.HealthTrack.domain.consultation.ConsultationStatus;
import br.com.nicolasfrech.HealthTrack.infra.exception.ValidateException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ValidateCancelStatusTest {

    private ValidateCancelStatus validateCancelStatus = new ValidateCancelStatus();

    @Test
    @DisplayName("Should cancel consultation with 24 hours advance notice")
    public void cancelStatusScenario01() {
        Consultation consultation = new Consultation(1L, 1L, LocalDateTime.now().plusHours(25));
        UpdateStatusDTO dto = new UpdateStatusDTO(ConsultationStatus.CANCELED);

        assertDoesNotThrow(() -> validateCancelStatus.validate(consultation, dto));
    }

    @Test
    @DisplayName("Should cancel consultation with 24 hours advance notice")
    public void cancelStatusScenario02() {
        Consultation consultation = new Consultation(1L, 1L, LocalDateTime.now().plusHours(23));
        UpdateStatusDTO dto = new UpdateStatusDTO(ConsultationStatus.CANCELED);

        assertThrows(ValidateException.class, () -> validateCancelStatus.validate(consultation, dto));
    }
}
