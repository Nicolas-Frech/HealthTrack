package br.com.nicolasfrech.HealthTrack.application.consultation.dto;

import java.time.LocalDateTime;

public record BookConsultationDTO(String medicCRM, String patientCPF, LocalDateTime date) {
}
