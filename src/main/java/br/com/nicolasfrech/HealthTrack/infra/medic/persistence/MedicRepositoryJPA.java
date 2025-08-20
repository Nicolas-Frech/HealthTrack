package br.com.nicolasfrech.HealthTrack.infra.medic.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicRepositoryJPA extends JpaRepository<MedicEntity, Long> {
}
