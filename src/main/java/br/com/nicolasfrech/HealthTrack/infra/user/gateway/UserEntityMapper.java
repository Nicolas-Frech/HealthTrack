package br.com.nicolasfrech.HealthTrack.infra.user.gateway;

import br.com.nicolasfrech.HealthTrack.domain.user.User;
import br.com.nicolasfrech.HealthTrack.infra.user.persistence.UserEntity;

public class UserEntityMapper {

    public User toDomain(UserEntity entity) {
        return new User(entity.getId(),
                entity.getUsername(),
                entity.getPassword(),
                entity.getRole());
    }

    public UserEntity toEntity(User user) {
        return new UserEntity(user.getId(),
                user.getUsername(),
                user.getPassword(),
                user.getRole());
    }
}
