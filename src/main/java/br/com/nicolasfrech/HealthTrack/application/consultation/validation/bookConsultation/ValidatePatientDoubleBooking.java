package br.com.nicolasfrech.HealthTrack.application.consultation.validation.bookConsultation;

import br.com.nicolasfrech.HealthTrack.application.consultation.dto.BookConsultationDTO;
import br.com.nicolasfrech.HealthTrack.application.consultation.gateway.ConsultationRepository;
import br.com.nicolasfrech.HealthTrack.application.patient.gateway.PatientRepository;
import br.com.nicolasfrech.HealthTrack.domain.consultation.ConsultationStatus;
import br.com.nicolasfrech.HealthTrack.domain.patient.Patient;
import br.com.nicolasfrech.HealthTrack.infra.exception.ValidateException;
import org.springframework.stereotype.Component;

@Component
public class ValidatePatientDoubleBooking implements BookConsultationValidation {

    private final ConsultationRepository consultationRepository;
    private final PatientRepository patientRepository;

    public ValidatePatientDoubleBooking(ConsultationRepository consultationRepository, PatientRepository patientRepository) {
        this.consultationRepository = consultationRepository;
        this.patientRepository = patientRepository;
    }

    @Override
    public void validate(BookConsultationDTO dto) {
        Patient patient = patientRepository.findByCpfAndActiveTrue(dto.patientCPF());
        boolean patientHasOtherConsultation = consultationRepository
                .existsByPatientIdAndDateAndStatus(patient.getId(), dto.date(), ConsultationStatus.SCHEDULED);

        if(patientHasOtherConsultation) {
            throw new ValidateException("Paciente já possui uma consulta agendada nesse horário!");
        }
    }
}
