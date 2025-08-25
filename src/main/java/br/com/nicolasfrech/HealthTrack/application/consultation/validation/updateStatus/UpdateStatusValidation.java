package br.com.nicolasfrech.HealthTrack.application.consultation.validation.updateStatus;

import br.com.nicolasfrech.HealthTrack.application.consultation.dto.UpdateStatusDTO;
import br.com.nicolasfrech.HealthTrack.domain.consultation.Consultation;

public interface UpdateStatusValidation {

    void validate(Consultation consultation, UpdateStatusDTO dto);

}
