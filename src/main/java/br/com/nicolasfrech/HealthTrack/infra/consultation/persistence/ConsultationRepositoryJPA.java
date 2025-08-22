package br.com.nicolasfrech.HealthTrack.infra.consultation.persistence;

import br.com.nicolasfrech.HealthTrack.domain.consultation.ConsultationStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface ConsultationRepositoryJPA extends JpaRepository<ConsultationEntity, Long> {
    boolean existsByPatientIdAndDateAndStatus(Long id, LocalDateTime date, ConsultationStatus consultationStatus);
}
