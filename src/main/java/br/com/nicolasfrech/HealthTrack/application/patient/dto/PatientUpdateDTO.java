package br.com.nicolasfrech.HealthTrack.application.patient.dto;

import jakarta.validation.constraints.NotNull;

public record PatientUpdateDTO(
        @NotNull
        Long id,
        String email,
        Integer age,
        String telephone) {
}
