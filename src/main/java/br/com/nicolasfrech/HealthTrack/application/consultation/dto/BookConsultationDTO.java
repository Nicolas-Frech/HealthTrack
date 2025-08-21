package br.com.nicolasfrech.HealthTrack.application.consultation.dto;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record BookConsultationDTO(
        @NotNull
        String medicCRM,
        @NotNull
        String patientCPF,
        @NotNull
        LocalDateTime date) {
}
