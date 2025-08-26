package br.com.nicolasfrech.HealthTrack.infra.user.gateway;

import br.com.nicolasfrech.HealthTrack.application.user.gateway.UserRepository;
import br.com.nicolasfrech.HealthTrack.domain.user.User;
import br.com.nicolasfrech.HealthTrack.infra.user.persistence.UserEntity;
import br.com.nicolasfrech.HealthTrack.infra.user.persistence.UserRepositoryJPA;

public class UserRepositoryImpl implements UserRepository {

    private final UserEntityMapper mapper;
    private final UserRepositoryJPA jpaRepository;

    public UserRepositoryImpl(UserEntityMapper mapper, UserRepositoryJPA jpaRepository) {
        this.mapper = mapper;
        this.jpaRepository = jpaRepository;
    }

    @Override
    public User save(User user) {
        UserEntity entity = mapper.toEntity(user);
        jpaRepository.save(entity);

        return mapper.toDomain(entity);
    }
}
