package br.com.nicolasfrech.HealthTrack.application.patient.dto;

import jakarta.validation.constraints.NotNull;

public record PatientRegistDTO(
        @NotNull
        String name,

        @NotNull
        String cpf,

        @NotNull
        Integer age,

        String email,

        @NotNull
        String telephone) {
}
