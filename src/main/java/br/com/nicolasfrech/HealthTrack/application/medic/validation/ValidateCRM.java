package br.com.nicolasfrech.HealthTrack.application.medic.validation;

import br.com.nicolasfrech.HealthTrack.application.medic.dto.MedicRegistDTO;
import br.com.nicolasfrech.HealthTrack.application.medic.gateway.MedicRepository;
import br.com.nicolasfrech.HealthTrack.infra.exception.ValidateException;
import org.springframework.stereotype.Component;

@Component
public class ValidateCRM implements MedicRegistValidation {

    private final MedicRepository medicRepository;


    public ValidateCRM(MedicRepository medicRepository) {
        this.medicRepository = medicRepository;
    }


    @Override
    public void validate(MedicRegistDTO dto) {
        if(medicRepository.existsByCrm(dto.crm())) {
            throw new ValidateException("Já existe médico registrado com esse CRM!");
        }
    }
}
