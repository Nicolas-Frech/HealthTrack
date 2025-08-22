package br.com.nicolasfrech.HealthTrack.application.consultation.validation.bookConsultation;

import br.com.nicolasfrech.HealthTrack.application.consultation.dto.BookConsultationDTO;
import br.com.nicolasfrech.HealthTrack.application.medic.gateway.MedicRepository;
import br.com.nicolasfrech.HealthTrack.application.patient.gateway.PatientRepository;
import br.com.nicolasfrech.HealthTrack.infra.exception.ValidateException;
import org.springframework.stereotype.Component;

@Component
public class ValidateActive implements BookConsultationValidation {

    private final MedicRepository medicRepository;
    private final PatientRepository patientRepository;

    public ValidateActive(MedicRepository medicRepository, PatientRepository patientRepository) {
        this.medicRepository = medicRepository;
        this.patientRepository = patientRepository;
    }

    @Override
    public void validate(BookConsultationDTO dto) {
        if(!medicRepository.existsByCrmAndActiveTrue(dto.medicCRM())) {
            throw new ValidateException("Não existe médico ativo com esse CRM!");
        }

        if(!patientRepository.existsByCpfAndActiveTrue(dto.patientCPF())) {
            throw new ValidateException("Não existe paciente ativo com esse CPF!");
        }
    }
}
