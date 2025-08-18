package br.com.nicolasfrech.HealthTrack.infra.patient.gateway;

import br.com.nicolasfrech.HealthTrack.application.patient.gateway.PatientRepository;
import br.com.nicolasfrech.HealthTrack.domain.patient.Patient;
import br.com.nicolasfrech.HealthTrack.infra.patient.persistence.PatientEntity;
import br.com.nicolasfrech.HealthTrack.infra.patient.persistence.PatientRepositoryJPA;

public class PatientRepositoryImpl implements PatientRepository {

    private final PatientEntityMapper mapper;
    private final PatientRepositoryJPA jpaRepository;

    public PatientRepositoryImpl(PatientEntityMapper mapper, PatientRepositoryJPA jpaRepository) {
        this.mapper = mapper;
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Patient save(Patient patient) {
        PatientEntity entity = mapper.toEntity(patient);

        jpaRepository.save(entity);
        return mapper.toDomain(entity);
    }
}
