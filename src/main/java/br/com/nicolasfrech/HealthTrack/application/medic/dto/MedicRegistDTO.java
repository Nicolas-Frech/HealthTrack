package br.com.nicolasfrech.HealthTrack.application.medic.dto;

import br.com.nicolasfrech.HealthTrack.domain.medic.Speciality;
import jakarta.validation.constraints.NotNull;

public record MedicRegistDTO(
        @NotNull
        String name,
        @NotNull
        String crm,
        @NotNull
        Speciality speciality,
        @NotNull
        String telephone,

        String email) {
}
