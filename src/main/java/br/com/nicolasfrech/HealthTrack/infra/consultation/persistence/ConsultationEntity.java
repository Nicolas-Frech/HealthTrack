package br.com.nicolasfrech.HealthTrack.infra.consultation.persistence;

import br.com.nicolasfrech.HealthTrack.domain.consultation.ConsultationStatus;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@Entity
@Table(name = "consultations")
@EqualsAndHashCode(of = "id")
public class ConsultationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long medicId;
    private Long patientId;
    private LocalDateTime date;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String notes;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String prescription;

    @Enumerated(EnumType.STRING)
    private ConsultationStatus status;

    public ConsultationEntity() {
    }

    public ConsultationEntity(Long id, Long medicId, Long patientId, LocalDateTime date, String notes, String prescription, ConsultationStatus status) {
        this.id = id;
        this.medicId = medicId;
        this.patientId = patientId;
        this.date = date;
        this.notes = notes;
        this.prescription = prescription;
        this.status = status;
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
