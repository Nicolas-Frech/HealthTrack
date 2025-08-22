package br.com.nicolasfrech.HealthTrack.application.consultation.validation.bookConsultation;

import br.com.nicolasfrech.HealthTrack.application.consultation.dto.BookConsultationDTO;
import br.com.nicolasfrech.HealthTrack.infra.exception.ValidateException;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class ValidateAdvanceNotice implements BookConsultationValidation {

    @Override
    public void validate(BookConsultationDTO dto) {
        LocalDateTime consultationDate = dto.date();
        LocalDateTime now = LocalDateTime.now();
        var diferenceBetween = Duration.between(consultationDate, now).toHours();

        if(diferenceBetween < 60) {
            throw new ValidateException("Consulta deve ser agendada com no mínimo 1 hora de antecedência!");
        }
    }
}
