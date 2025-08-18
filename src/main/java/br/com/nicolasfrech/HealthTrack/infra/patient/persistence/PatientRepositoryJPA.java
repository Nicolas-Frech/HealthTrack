package br.com.nicolasfrech.HealthTrack.infra.patient.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepositoryJPA extends JpaRepository<PatientEntity, Long> {

    PatientEntity findByIdAndActiveTrue(Long id);
}
