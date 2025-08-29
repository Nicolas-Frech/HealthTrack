package br.com.nicolasfrech.HealthTrack.domain.consultation;

import br.com.nicolasfrech.HealthTrack.domain.medic.Medic;
import br.com.nicolasfrech.HealthTrack.domain.patient.Patient;

import java.time.LocalDateTime;

public class Consultation {

    private Long id;
    private Medic medic;
    private Patient patient;
    private LocalDateTime date;
    private String notes;
    private String prescription;
    private ConsultationStatus status;

    public Consultation() {
    }

    public Consultation(Long id, Medic medic, Patient patient, LocalDateTime date, String notes, String prescription, ConsultationStatus status) {
        this.id = id;
        this.medic = medic;
        this.patient = patient;
        this.date = date;
        this.notes = notes;
        this.prescription = prescription;
        this.status = status;
    }

    public Consultation(Medic medic, Patient patient, LocalDateTime date) {
        this.medic = medic;
        this.patient = patient;
        this.date = date;
        this.status = ConsultationStatus.SCHEDULED;
    }

    public void updateStatus(ConsultationStatus status) {
        if(status != null) {
            this.status = status;
        }
    }

    public void changeDate(LocalDateTime date) {
        if(date != null) {
            this.date = date;
        }
    }

    public void addNotes(String notes) {
        if(notes != null && !notes.isBlank()) {
            this.notes = notes;
        }
    }

    public void addPrescription(String prescription) {
        if(prescription != null && !prescription.isBlank()) {
            this.prescription = prescription;
        }
    }

    public Long getId() {
        return id;
    }

    public Medic getMedic() {
        return medic;
    }

    public Patient getPatient() {
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
