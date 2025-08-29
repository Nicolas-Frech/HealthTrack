package br.com.nicolasfrech.HealthTrack.application.consultation.dto;

import br.com.nicolasfrech.HealthTrack.application.medic.dto.MedicReturnDTO;
import br.com.nicolasfrech.HealthTrack.application.patient.dto.PatientReturnDTO;
import br.com.nicolasfrech.HealthTrack.domain.consultation.Consultation;
import br.com.nicolasfrech.HealthTrack.domain.consultation.ConsultationStatus;
import br.com.nicolasfrech.HealthTrack.domain.medic.Medic;

import java.time.LocalDateTime;

public record ConsultationReturnDTO(Long id, MedicReturnDTO medic, PatientReturnDTO patient, LocalDateTime date, ConsultationStatus status, String notes, String prescription) {
    public ConsultationReturnDTO(Consultation consultation) {
        this(consultation.getId(), new MedicReturnDTO(consultation.getMedic()), new PatientReturnDTO(consultation.getPatient()), consultation.getDate(),
                consultation.getStatus(), consultation.getNotes(), consultation.getPrescription());
    }
}
