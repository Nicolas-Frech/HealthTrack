package br.com.nicolasfrech.HealthTrack.application.patient.dto;

import br.com.nicolasfrech.HealthTrack.domain.patient.Patient;

public record PatientReturnDTO(Long id, String name, String cpf, String telephone, Integer age, String email) {
    public PatientReturnDTO(Patient patient) {
        this(patient.getId(), patient.getName(), patient.getCpf(), patient.getTelephone(),
                patient.getAge(), patient.getEmail());
    }
}
