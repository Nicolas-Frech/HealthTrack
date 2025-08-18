package br.com.nicolasfrech.HealthTrack.infra.patient.gateway;

import br.com.nicolasfrech.HealthTrack.domain.patient.Patient;
import br.com.nicolasfrech.HealthTrack.infra.patient.persistence.PatientEntity;

public class PatientEntityMapper {

    public PatientEntity toEntity(Patient patient) {
        return new PatientEntity(patient.getTelephone(),
                patient.getEmail(),
                patient.getAge(),
                patient.getCpf(),
                patient.getName(),
                patient.getId());
    }

    public Patient toDomain(PatientEntity entity) {
        return new Patient(entity.getId(), entity.getTelephone(),
                entity.getName(),
                entity.getCpf(),
                entity.getAge(),
                entity.getEmail());
    }
}
