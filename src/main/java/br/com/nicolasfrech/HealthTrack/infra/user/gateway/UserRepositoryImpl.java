package br.com.nicolasfrech.HealthTrack.infra.user.gateway;

import br.com.nicolasfrech.HealthTrack.application.user.gateway.UserRepository;
import br.com.nicolasfrech.HealthTrack.domain.user.Role;
import br.com.nicolasfrech.HealthTrack.domain.user.User;
import br.com.nicolasfrech.HealthTrack.infra.user.persistence.UserEntity;
import br.com.nicolasfrech.HealthTrack.infra.user.persistence.UserRepositoryJPA;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserRepositoryImpl implements UserRepository , UserDetailsService {

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

    @Override
    public boolean existsByRole(Role role) {
        return jpaRepository.existsByRole(role);
    }

    @Override
    public User findByUsername(String username) {
        UserEntity entity = jpaRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));
        return mapper.toDomain(entity);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return jpaRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));
    }
}
