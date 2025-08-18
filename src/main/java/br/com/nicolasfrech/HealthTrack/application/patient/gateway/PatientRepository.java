package br.com.nicolasfrech.HealthTrack.application.patient.gateway;

import br.com.nicolasfrech.HealthTrack.domain.Patient;

public interface PatientRepository {

    Patient save(Patient patient);
}
