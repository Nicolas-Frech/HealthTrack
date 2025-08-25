package br.com.nicolasfrech.HealthTrack.domain.consultation;

import java.time.LocalDateTime;

public class Consultation {

    private Long id;
    private Long medicId;
    private Long patientId;
    private LocalDateTime date;
    private String notes;
    private String prescription;
    private ConsultationStatus status;

    public Consultation() {
    }

    public Consultation(Long id, Long medicId, Long patientId, LocalDateTime date, String notes, String prescription, ConsultationStatus status) {
        this.id = id;
        this.medicId = medicId;
        this.patientId = patientId;
        this.date = date;
        this.notes = notes;
        this.prescription = prescription;
        this.status = status;
    }

    public Consultation(Long medicId, Long patientId, LocalDateTime date) {
        this.medicId = medicId;
        this.patientId = patientId;
        this.date = date;
        this.status = ConsultationStatus.SCHEDULED;
    }

    public void updateStatus(ConsultationStatus status) {
        if(status != null) {
            this.status = status;
        }
    }

    public Long getId() {
        return id;
    }

    public Long getMedicId() {
        return medicId;
    }

    public Long getPatientId() {
        return patientId;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public String getNotes() {
        return notes;
    }

    public String getPrescription() {
        return prescription;
    }

    public ConsultationStatus getStatus() {
        return status;
    }
}
