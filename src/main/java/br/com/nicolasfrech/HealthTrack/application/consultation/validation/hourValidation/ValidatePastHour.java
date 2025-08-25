package br.com.nicolasfrech.HealthTrack.application.consultation.validation.hourValidation;

import br.com.nicolasfrech.HealthTrack.domain.consultation.Consultation;
import br.com.nicolasfrech.HealthTrack.infra.exception.ValidateException;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ValidatePastHour implements HourValidation {

    @Override
    public void validate(Consultation consultation, LocalDateTime date) {
        if(date.isBefore(LocalDateTime.now())) {
            throw new ValidateException("Consulta não pode ser marcada no passado!");
        }
    }
}
