package br.com.nicolasfrech.HealthTrack.infra.medic.gateway;

import br.com.nicolasfrech.HealthTrack.application.medic.gateway.MedicRepository;
import br.com.nicolasfrech.HealthTrack.domain.medic.Medic;
import br.com.nicolasfrech.HealthTrack.infra.medic.persistence.MedicEntity;
import br.com.nicolasfrech.HealthTrack.infra.medic.persistence.MedicRepositoryJPA;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public class MedicRepositoryImpl implements MedicRepository {

    private final MedicRepositoryJPA jpaRepository;
    private final MedicEntityMapper mapper;

    public MedicRepositoryImpl(MedicRepositoryJPA jpaRepository, MedicEntityMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public Medic save(Medic medic) {
        MedicEntity entity = mapper.toEntity(medic);

        jpaRepository.save(entity);
        return mapper.toDomain(entity);
    }

    @Override
    public Medic findByIdAndActiveTrue(Long id) {
        MedicEntity entity = jpaRepository.findByIdAndActiveTrue(id);

        return mapper.toDomain(entity);
    }

    @Override
    public Medic findById(Long id) {
        MedicEntity entity = jpaRepository.getReferenceById(id);

        return mapper.toDomain(entity);
    }

    @Override
    public Page<Medic> findAllByActiveTrue(Pageable pageable) {
        Page<MedicEntity> entities = jpaRepository.findAllByActiveTrue(pageable);
        return entities.map(mapper::toDomain);
    }
}
