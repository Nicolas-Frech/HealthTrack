package br.com.nicolasfrech.HealthTrack.infra.user.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepositoryJPA extends JpaRepository<UserEntity, Long> {
}
