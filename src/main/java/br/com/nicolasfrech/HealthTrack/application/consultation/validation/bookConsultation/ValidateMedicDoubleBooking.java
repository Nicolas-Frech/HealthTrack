package br.com.nicolasfrech.HealthTrack.application.consultation.validation.bookConsultation;

import br.com.nicolasfrech.HealthTrack.application.consultation.dto.BookConsultationDTO;
import br.com.nicolasfrech.HealthTrack.application.consultation.gateway.ConsultationRepository;
import br.com.nicolasfrech.HealthTrack.application.medic.gateway.MedicRepository;
import br.com.nicolasfrech.HealthTrack.application.patient.gateway.PatientRepository;
import br.com.nicolasfrech.HealthTrack.domain.consultation.ConsultationStatus;
import br.com.nicolasfrech.HealthTrack.domain.medic.Medic;
import br.com.nicolasfrech.HealthTrack.domain.patient.Patient;
import br.com.nicolasfrech.HealthTrack.infra.exception.ValidateException;
import org.springframework.stereotype.Component;

@Component
public class ValidateMedicDoubleBooking implements BookConsultationValidation {

    private final ConsultationRepository consultationRepository;
    private final MedicRepository medicRepository;

    public ValidateMedicDoubleBooking(ConsultationRepository consultationRepository, MedicRepository medicRepository) {
        this.consultationRepository = consultationRepository;
        this.medicRepository = medicRepository;
    }

    @Override
    public void validate(BookConsultationDTO dto) {
        Medic medic = medicRepository.findByCrmAndActiveTrue(dto.medicCRM());
        boolean medicHasOtherConsultation = consultationRepository
                .existsByMedicIdAndDateAndStatus(medic.getId(), dto.date(), ConsultationStatus.SCHEDULED);

        if(medicHasOtherConsultation) {
            throw new ValidateException("Médico já possui uma consulta agendada nesse horário!");
        }
    }
}
