package br.com.nicolasfrech.HealthTrack.application.medic.gateway;

import br.com.nicolasfrech.HealthTrack.domain.medic.Medic;

public interface MedicRepository {

    Medic save(Medic medic);

    Medic findByIdAndActiveTrue(Long id);

    Medic findById(Long id);
}
