package br.com.nicolasfrech.HealthTrack.application.consultation.validation;

import br.com.nicolasfrech.HealthTrack.application.consultation.dto.BookConsultationDTO;
import br.com.nicolasfrech.HealthTrack.infra.exception.ValidateException;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

@Component
public class ValidateHour implements BookConsultationValidation {

    @Override
    public void validate(BookConsultationDTO dto) {
        LocalDateTime consultationDate = dto.date();

        boolean sunday = consultationDate.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        boolean beforeOpening = consultationDate.getHour() < 8;
        boolean afterClosing = consultationDate.getHour() > 18;

        if(sunday || beforeOpening || afterClosing) {
            throw new ValidateException("Consulta fora do horário de funcionamento!");
        }
    }
}
