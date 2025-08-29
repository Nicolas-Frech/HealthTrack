package br.com.nicolasfrech.HealthTrack.application.consultation.validation.hourValidation;

import br.com.nicolasfrech.HealthTrack.application.consultation.gateway.ConsultationRepository;
import br.com.nicolasfrech.HealthTrack.domain.consultation.Consultation;
import br.com.nicolasfrech.HealthTrack.domain.consultation.ConsultationStatus;
import br.com.nicolasfrech.HealthTrack.infra.exception.ValidateException;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ValidatePatientDoubleBooking implements HourValidation {

    private final ConsultationRepository consultationRepository;

    public ValidatePatientDoubleBooking(ConsultationRepository consultationRepository) {
        this.consultationRepository = consultationRepository;
    }

    @Override
    public void validate(Consultation consultation, LocalDateTime newDate) {
        boolean patientHasOtherConsultation = consultationRepository
                .existsByPatientIdAndDateAndStatus(
                        consultation.getPatient().getId(),
                        newDate,
                        ConsultationStatus.SCHEDULED
                );

        if (patientHasOtherConsultation) {
            throw new ValidateException("Paciente já possui uma consulta agendada nesse horário!");
        }
    }
}

