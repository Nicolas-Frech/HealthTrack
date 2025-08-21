package br.com.nicolasfrech.HealthTrack.application.medic.validation;

import br.com.nicolasfrech.HealthTrack.application.medic.dto.MedicRegistDTO;
import br.com.nicolasfrech.HealthTrack.application.medic.gateway.MedicRepository;
import br.com.nicolasfrech.HealthTrack.infra.exception.ValidateException;
import org.springframework.stereotype.Component;

@Component
public class ValidateMedicTelephone implements RegistValidation {

    private final MedicRepository medicRepository;


    public ValidateMedicTelephone(MedicRepository medicRepository) {
        this.medicRepository = medicRepository;
    }

    @Override
    public void validate(MedicRegistDTO dto) {
        if(medicRepository.existsByTelephone(dto.telephone())) {
            throw new ValidateException("Já existe médico registrado com esse telefone!");
        }
    }
}
