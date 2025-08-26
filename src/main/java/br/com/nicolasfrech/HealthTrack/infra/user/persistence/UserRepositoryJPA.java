package br.com.nicolasfrech.HealthTrack.infra.user.persistence;

import br.com.nicolasfrech.HealthTrack.domain.user.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepositoryJPA extends JpaRepository<UserEntity, Long> {
    boolean existsByRole(Role role);
}
