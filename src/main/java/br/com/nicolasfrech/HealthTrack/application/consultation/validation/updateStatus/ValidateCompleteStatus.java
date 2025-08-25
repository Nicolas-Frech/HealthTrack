package br.com.nicolasfrech.HealthTrack.application.consultation.validation.updateStatus;

import br.com.nicolasfrech.HealthTrack.application.consultation.dto.UpdateStatusDTO;
import br.com.nicolasfrech.HealthTrack.domain.consultation.Consultation;
import br.com.nicolasfrech.HealthTrack.domain.consultation.ConsultationStatus;
import br.com.nicolasfrech.HealthTrack.infra.exception.ValidateException;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ValidateCompleteStatus implements UpdateStatusValidation {

    @Override
    public void validate(Consultation consultation, UpdateStatusDTO dto) {
        if (dto.status() != ConsultationStatus.COMPLETED) {
            return;
        }

        if (consultation.getDate().isAfter(LocalDateTime.now())) {
            throw new ValidateException("Não é possível concluir uma consulta antes do horário agendado!");
        }

        if (consultation.getStatus() == ConsultationStatus.CANCELED) {
            throw new ValidateException("Não é possível concluir uma consulta já cancelada!");
        }
    }
}
