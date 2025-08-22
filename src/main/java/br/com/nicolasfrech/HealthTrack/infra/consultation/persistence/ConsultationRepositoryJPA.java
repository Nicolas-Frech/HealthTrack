package br.com.nicolasfrech.HealthTrack.infra.consultation.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ConsultationRepositoryJPA extends JpaRepository<ConsultationEntity, Long> {
}
