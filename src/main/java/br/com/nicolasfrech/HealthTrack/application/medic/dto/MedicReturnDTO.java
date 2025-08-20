package br.com.nicolasfrech.HealthTrack.application.medic.dto;

import br.com.nicolasfrech.HealthTrack.domain.medic.Medic;
import br.com.nicolasfrech.HealthTrack.domain.medic.Speciality;

public record MedicReturnDTO(Long id, String name, String crm, Speciality speciality,
                             String telephone, String email, Boolean active) {

    public MedicReturnDTO(Medic medic) {
        this(medic.getId(), medic.getName(), medic.getCrm(), medic.getSpeciality()
        , medic.getTelephone(), medic.getEmail(), medic.getActive());
    }
}
