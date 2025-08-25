package br.com.nicolasfrech.HealthTrack.application.consultation.dto;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record ChangeDateDTO(
        @NotNull
        LocalDateTime date) {
}
