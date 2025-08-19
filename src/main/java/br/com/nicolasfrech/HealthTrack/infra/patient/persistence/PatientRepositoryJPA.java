package br.com.nicolasfrech.HealthTrack.infra.patient.persistence;

import br.com.nicolasfrech.HealthTrack.domain.patient.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepositoryJPA extends JpaRepository<PatientEntity, Long> {

    PatientEntity findByIdAndActiveTrue(Long id);

    Page<PatientEntity> findAllByActiveTrue(Pageable pageable);
}
