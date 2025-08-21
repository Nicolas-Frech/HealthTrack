package br.com.nicolasfrech.HealthTrack.infra.patient.gateway;

import br.com.nicolasfrech.HealthTrack.application.patient.gateway.PatientRepository;
import br.com.nicolasfrech.HealthTrack.domain.patient.Patient;
import br.com.nicolasfrech.HealthTrack.infra.patient.persistence.PatientEntity;
import br.com.nicolasfrech.HealthTrack.infra.patient.persistence.PatientRepositoryJPA;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

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

    @Override
    public Patient findByIdAndActiveTrue(Long id) {
        PatientEntity patient = jpaRepository.findByIdAndActiveTrue(id);

        return mapper.toDomain(patient);
    }

    @Override
    public Patient findById(Long id) {
        PatientEntity patient = jpaRepository.getReferenceById(id);
        return mapper.toDomain(patient);
    }

    @Override
    public Page<Patient> findAllByActiveTrue(Pageable pageable) {
        Page<PatientEntity> page = jpaRepository.findAllByActiveTrue(pageable);
        return page.map(mapper::toDomain);
    }

    @Override
    public boolean existsByCpf(String cpf) {
        return jpaRepository.existsByCpf(cpf);
    }

    @Override
    public boolean existsByTelephone(String telephone) {
        return jpaRepository.existsByTelephone(telephone);
    }

    @Override
    public Patient findByCpfAndActiveTrue(String cpf) {
        PatientEntity entity = jpaRepository.findByCpfAndActiveTrue(cpf);

        return mapper.toDomain(entity);
    }
}
