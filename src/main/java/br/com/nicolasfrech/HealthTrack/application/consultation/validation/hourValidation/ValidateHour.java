package br.com.nicolasfrech.HealthTrack.application.consultation.validation.hourValidation;

import br.com.nicolasfrech.HealthTrack.domain.consultation.Consultation;
import br.com.nicolasfrech.HealthTrack.infra.exception.ValidateException;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

@Component
public class ValidateHour implements HourValidation {

    @Override
    public void validate(Consultation consultation, LocalDateTime date) {
        boolean sunday = date.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        boolean beforeOpening = date.getHour() < 8;
        boolean afterClosing = date.getHour() > 18;

        if(sunday || beforeOpening || afterClosing) {
            throw new ValidateException("Consulta fora do horário de funcionamento!");
        }
    }
}
