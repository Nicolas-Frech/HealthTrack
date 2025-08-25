package br.com.nicolasfrech.HealthTrack.application.consultation.gateway;

import br.com.nicolasfrech.HealthTrack.domain.consultation.Consultation;
import br.com.nicolasfrech.HealthTrack.domain.consultation.ConsultationStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;

public interface ConsultationRepository {

    Consultation save(Consultation consultation);

    boolean existsByPatientIdAndDateAndStatus(Long id, LocalDateTime date, ConsultationStatus consultationStatus);

    boolean existsByMedicIdAndDateAndStatus(Long id, LocalDateTime date, ConsultationStatus consultationStatus);

    Consultation getReferenceById(Long id);

    Page<Consultation> findAll(Pageable pageable);

    Page<Consultation> findAllByMedicId(Pageable pageable, Long id);
}
