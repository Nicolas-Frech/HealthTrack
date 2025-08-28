package br.com.nicolasfrech.HealthTrack.infra.user.gateway;

import br.com.nicolasfrech.HealthTrack.application.user.gateway.UserRepository;
import br.com.nicolasfrech.HealthTrack.domain.user.Role;
import br.com.nicolasfrech.HealthTrack.domain.user.User;
import br.com.nicolasfrech.HealthTrack.infra.medic.gateway.MedicEntityMapper;
import br.com.nicolasfrech.HealthTrack.infra.user.persistence.UserEntity;
import br.com.nicolasfrech.HealthTrack.infra.user.persistence.UserRepositoryJPA;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserRepositoryImpl implements UserRepository , UserDetailsService {

    private final UserEntityMapper userMapper;
    private final MedicEntityMapper medicMapper;
    private final UserRepositoryJPA jpaRepository;

    public UserRepositoryImpl(UserEntityMapper mapper, MedicEntityMapper medicMapper, UserRepositoryJPA jpaRepository) {
        this.userMapper = mapper;
        this.medicMapper = medicMapper;
        this.jpaRepository = jpaRepository;
    }

    @Override
    public User save(User user) {
        UserEntity entity = userMapper.toEntity(user);
        if(user.getRole() == Role.MEDIC) {
            entity.setMedic(medicMapper.toEntity(user.getMedic()));
        }

        jpaRepository.save(entity);

        return userMapper.toDomain(entity);
    }

    @Override
    public boolean existsByRole(Role role) {
        return jpaRepository.existsByRole(role);
    }

    @Override
    public User findByUsername(String username) {
        UserEntity entity = jpaRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));
        return userMapper.toDomain(entity);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return jpaRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));
    }
}
