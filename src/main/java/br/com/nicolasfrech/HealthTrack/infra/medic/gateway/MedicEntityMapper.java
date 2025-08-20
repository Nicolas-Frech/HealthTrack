package br.com.nicolasfrech.HealthTrack.infra.medic.gateway;

import br.com.nicolasfrech.HealthTrack.domain.medic.Medic;
import br.com.nicolasfrech.HealthTrack.infra.medic.persistence.MedicEntity;

public class MedicEntityMapper {

    public MedicEntity toEntity(Medic medic) {
        return new MedicEntity(medic.getId(),
                medic.getName(),
                medic.getCrm(),
                medic.getSpeciality(),
                medic.getTelephone(),
                medic.getEmail(),
                medic.getActive());
    }

    public Medic toDomain(MedicEntity entity) {
        return new Medic(entity.getId(),
                entity.getName(),
                entity.getCrm(),
                entity.getSpeciality(),
                entity.getTelephone(),
                entity.getEmail(),
                entity.getActive());
    }
}
