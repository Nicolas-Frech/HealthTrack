package br.com.nicolasfrech.HealthTrack.application.patient.validation;

import br.com.nicolasfrech.HealthTrack.application.patient.dto.PatientRegistDTO;
import br.com.nicolasfrech.HealthTrack.application.patient.gateway.PatientRepository;
import br.com.nicolasfrech.HealthTrack.infra.exception.ValidateException;
import org.springframework.stereotype.Component;

@Component
public class ValidateCPF implements PatientRegistValidation {

    private final PatientRepository patientRepository;

    public ValidateCPF(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Override
    public void validate(PatientRegistDTO dto) {
        if(patientRepository.existsByCpf(dto.cpf())) {
            throw new ValidateException("Já existe paciente registrado com esse CPF!");
        }
    }
}
