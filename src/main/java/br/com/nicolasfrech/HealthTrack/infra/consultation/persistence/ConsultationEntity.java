package br.com.nicolasfrech.HealthTrack.infra.consultation.persistence;

import br.com.nicolasfrech.HealthTrack.domain.consultation.ConsultationStatus;
import br.com.nicolasfrech.HealthTrack.infra.medic.persistence.MedicEntity;
import br.com.nicolasfrech.HealthTrack.infra.patient.persistence.PatientEntity;
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

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "medic_id", nullable = false)
    private MedicEntity medic;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id", nullable = false)
    private PatientEntity patient;

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

    public ConsultationEntity(Long id, MedicEntity medic, PatientEntity patient, LocalDateTime date, String notes, String prescription, ConsultationStatus status) {
        this.id = id;
        this.medic = medic;
        this.patient = patient;
        this.date = date;
        this.notes = notes;
        this.prescription = prescription;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public MedicEntity getMedic() {
        return medic;
    }

    public PatientEntity getPatient() {
        return patient;
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
