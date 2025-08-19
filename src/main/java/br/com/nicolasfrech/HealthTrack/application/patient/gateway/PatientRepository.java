package br.com.nicolasfrech.HealthTrack.application.patient.gateway;

import br.com.nicolasfrech.HealthTrack.domain.patient.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PatientRepository {

    Patient save(Patient patient);

    Patient findByIdAndActiveTrue(Long id);

    Patient findById(Long id);

    Page<Patient> findAllByActiveTrue(Pageable pageable);

}
