package br.com.nicolasfrech.HealthTrack.application.consultation.validation.bookConsultation;

import br.com.nicolasfrech.HealthTrack.application.consultation.dto.BookConsultationDTO;
import br.com.nicolasfrech.HealthTrack.application.patient.gateway.PatientRepository;
import br.com.nicolasfrech.HealthTrack.infra.exception.ValidateException;
import org.springframework.stereotype.Component;

@Component
public class ValidatePatientActive implements BookConsultationValidation {

    private final PatientRepository patientRepository;

    public ValidatePatientActive(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Override
    public void validate(BookConsultationDTO dto) {
        if(!patientRepository.existsByCpfAndActiveTrue(dto.patientCPF())) {
            throw new ValidateException("Não existe paciente ativo com esse CPF!");
        }
    }
}
