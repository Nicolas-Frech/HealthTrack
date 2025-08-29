package br.com.nicolasfrech.HealthTrack.infra.consultation.gateway;

import br.com.nicolasfrech.HealthTrack.domain.consultation.Consultation;
import br.com.nicolasfrech.HealthTrack.infra.consultation.persistence.ConsultationEntity;
import br.com.nicolasfrech.HealthTrack.infra.medic.gateway.MedicEntityMapper;
import br.com.nicolasfrech.HealthTrack.infra.patient.gateway.PatientEntityMapper;

public class ConsultationEntityMapper {

    private final MedicEntityMapper medicMapper;
    private final PatientEntityMapper patientMapper;

    public ConsultationEntityMapper(MedicEntityMapper medicMapper, PatientEntityMapper patientMapper) {
        this.medicMapper = medicMapper;
        this.patientMapper = patientMapper;
    }

    public Consultation toDomain(ConsultationEntity entity) {
        return new Consultation(
                entity.getId(),
                medicMapper.toDomain(entity.getMedic()),
                patientMapper.toDomain(entity.getPatient()),
                entity.getDate(),
                entity.getNotes(),
                entity.getPrescription(),
                entity.getStatus()
        );
    }

    public ConsultationEntity toEntity(Consultation consultation) {
        return new ConsultationEntity(
                consultation.getId(),
                medicMapper.toEntity(consultation.getMedic()),
                patientMapper.toEntity(consultation.getPatient()),
                consultation.getDate(),
                consultation.getNotes(),
                consultation.getPrescription(),
                consultation.getStatus()
        );
    }
}
