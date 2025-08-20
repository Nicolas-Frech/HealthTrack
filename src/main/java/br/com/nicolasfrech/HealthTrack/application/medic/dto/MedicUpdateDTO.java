package br.com.nicolasfrech.HealthTrack.application.medic.dto;

import jakarta.validation.constraints.NotNull;

public record MedicUpdateDTO(
        @NotNull
        Long id,
        String telephone,
        String email) {
}
