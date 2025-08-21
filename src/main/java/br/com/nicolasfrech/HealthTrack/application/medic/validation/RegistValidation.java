package br.com.nicolasfrech.HealthTrack.application.medic.validation;

import br.com.nicolasfrech.HealthTrack.application.medic.dto.MedicRegistDTO;

public interface RegistValidation {

    void validate(MedicRegistDTO dto);
}
