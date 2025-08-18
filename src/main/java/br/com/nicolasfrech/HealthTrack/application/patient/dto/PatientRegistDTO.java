package br.com.nicolasfrech.HealthTrack.application.patient.dto;

import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.br.CPF;

public record PatientRegistDTO(
        @NotNull
        String name,

        @NotNull
        @CPF
        String cpf,

        @NotNull
        Integer age,

        String email,

        @NotNull
        String telephone) {
}
