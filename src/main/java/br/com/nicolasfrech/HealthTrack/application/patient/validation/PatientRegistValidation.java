package br.com.nicolasfrech.HealthTrack.application.patient.validation;

import br.com.nicolasfrech.HealthTrack.application.patient.dto.PatientRegistDTO;

public interface PatientRegistValidation {

    void validate(PatientRegistDTO dto);
}
