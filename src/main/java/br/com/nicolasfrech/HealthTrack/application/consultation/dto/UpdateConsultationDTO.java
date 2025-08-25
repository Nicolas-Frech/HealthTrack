package br.com.nicolasfrech.HealthTrack.application.consultation.dto;

import br.com.nicolasfrech.HealthTrack.domain.consultation.ConsultationStatus;
import jakarta.validation.constraints.NotNull;

public record UpdateConsultationDTO(
        @NotNull
        Long id,

        @NotNull
        ConsultationStatus status) {
}
