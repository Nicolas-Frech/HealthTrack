package br.com.nicolasfrech.HealthTrack.application.consultation.validation.bookConsultation;

import br.com.nicolasfrech.HealthTrack.application.consultation.dto.BookConsultationDTO;
import br.com.nicolasfrech.HealthTrack.application.medic.gateway.MedicRepository;
import br.com.nicolasfrech.HealthTrack.infra.exception.ValidateException;
import org.springframework.stereotype.Component;

@Component
public class ValidateMedicActive implements BookConsultationValidation {

    private final MedicRepository medicRepository;

    public ValidateMedicActive(MedicRepository medicRepository) {
        this.medicRepository = medicRepository;
    }

    @Override
    public void validate(BookConsultationDTO dto) {
        if(!medicRepository.existsByCrmAndActiveTrue(dto.medicCRM())) {
            throw new ValidateException("Não existe médico ativo com esse CRM!");
        }
    }
}
