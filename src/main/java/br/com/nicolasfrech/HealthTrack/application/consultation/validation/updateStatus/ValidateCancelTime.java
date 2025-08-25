package br.com.nicolasfrech.HealthTrack.application.consultation.validation.updateStatus;

import br.com.nicolasfrech.HealthTrack.application.consultation.dto.UpdateStatusDTO;
import br.com.nicolasfrech.HealthTrack.domain.consultation.Consultation;
import br.com.nicolasfrech.HealthTrack.domain.consultation.ConsultationStatus;
import br.com.nicolasfrech.HealthTrack.infra.exception.ValidateException;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ValidateCancelTime implements UpdateStatusValidation {


    @Override
    public void validate(Consultation consultation, UpdateStatusDTO dto) {
        if (dto.status() == ConsultationStatus.CANCELED) {
            if (consultation.getDate().minusHours(24).isBefore(LocalDateTime.now())) {
                throw new ValidateException("Não é possível cancelar com menos de 24h de antecedência.");
            }
        }
    }
}
