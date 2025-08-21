package br.com.nicolasfrech.HealthTrack.application.patient.validation;

import br.com.nicolasfrech.HealthTrack.application.patient.dto.PatientRegistDTO;
import br.com.nicolasfrech.HealthTrack.application.patient.gateway.PatientRepository;
import br.com.nicolasfrech.HealthTrack.infra.exception.ValidateException;
import org.springframework.stereotype.Component;

@Component
public class ValidatePatientTelephone implements PatientRegistValidation {

    private final PatientRepository patientRepository;

    public ValidatePatientTelephone(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Override
    public void validate(PatientRegistDTO dto) {
        if(patientRepository.existsByTelephone(dto.telephone())) {
            throw new ValidateException("Já existe paciente registrado com esse telefone!");
        }
    }
}
