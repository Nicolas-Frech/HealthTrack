package br.com.nicolasfrech.HealthTrack.infra.medic.gateway;

import br.com.nicolasfrech.HealthTrack.application.medic.gateway.MedicRepository;
import br.com.nicolasfrech.HealthTrack.domain.medic.Medic;
import br.com.nicolasfrech.HealthTrack.infra.medic.persistence.MedicEntity;
import br.com.nicolasfrech.HealthTrack.infra.medic.persistence.MedicRepositoryJPA;
import br.com.nicolasfrech.HealthTrack.infra.user.gateway.UserEntityMapper;
import br.com.nicolasfrech.HealthTrack.infra.user.persistence.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public class MedicRepositoryImpl implements MedicRepository {

    private final MedicRepositoryJPA jpaRepository;
    private final MedicEntityMapper mapper;
    private final UserEntityMapper userMapper;

    public MedicRepositoryImpl(MedicRepositoryJPA jpaRepository, MedicEntityMapper mapper, UserEntityMapper userMapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
        this.userMapper = userMapper;
    }

    @Override
    public Medic save(Medic medic) {
        MedicEntity entity = mapper.toEntity(medic);

        if(medic.getUser() != null) {
            entity.setUser(userMapper.toEntity(medic.getUser()));
        }

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

    @Override
    public boolean existsByCrm(String crm) {
        return jpaRepository.existsByCrm(crm);
    }

    @Override
    public boolean existsByTelephone(String telephone) {
        return jpaRepository.existsByTelephone(telephone);
    }

    @Override
    public Medic findByCrmAndActiveTrue(String crm) {
        MedicEntity entity = jpaRepository.findByCrmAndActiveTrue(crm);

        return mapper.toDomain(entity);
    }

    @Override
    public boolean existsByCrmAndActiveTrue(String crm) {
        return jpaRepository.existsByCrmAndActiveTrue(crm);
    }

    @Override
    public boolean existsByIdAndActiveTrue(Long id) {
        return jpaRepository.existsByIdAndActiveTrue(id);
    }
}
