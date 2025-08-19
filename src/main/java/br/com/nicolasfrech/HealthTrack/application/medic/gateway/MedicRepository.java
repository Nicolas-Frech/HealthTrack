package br.com.nicolasfrech.HealthTrack.application.medic.gateway;

import br.com.nicolasfrech.HealthTrack.domain.medic.Medic;

public interface MedicRepository {

    void save(Medic medic);
}
