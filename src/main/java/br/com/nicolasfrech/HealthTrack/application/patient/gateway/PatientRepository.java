package br.com.nicolasfrech.HealthTrack.application.patient.gateway;

import br.com.nicolasfrech.HealthTrack.domain.patient.Patient;

public interface PatientRepository {

    Patient save(Patient patient);

    Patient findByIdAndActiveTrue(Long id);

    Patient findById(Long id);

}
