package br.com.nicolasfrech.HealthTrack.application.consultation.dto;

import br.com.nicolasfrech.HealthTrack.domain.consultation.Consultation;
import br.com.nicolasfrech.HealthTrack.domain.consultation.ConsultationStatus;

import java.time.LocalDateTime;

public record ConsultationReturnDTO(Long id, Long medicId, Long patientId, LocalDateTime date, ConsultationStatus status) {
    public ConsultationReturnDTO(Consultation consultation) {
        this(consultation.getId(), consultation.getMedicId(), consultation.getPatientId(), consultation.getDate(),
                consultation.getStatus());
    }
}
