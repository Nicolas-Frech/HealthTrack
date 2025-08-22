package br.com.nicolasfrech.HealthTrack.infra.consultation.gateway;

import br.com.nicolasfrech.HealthTrack.domain.consultation.Consultation;
import br.com.nicolasfrech.HealthTrack.infra.consultation.persistence.ConsultationEntity;

public class ConsultationEntityMapper {

    public Consultation toDomain(ConsultationEntity entity) {
        return new Consultation(
                entity.getId(),
                entity.getMedicId(),
                entity.getPatientId(),
                entity.getDate(),
                entity.getNotes(),
                entity.getPrescription(),
                entity.getStatus()
        );
    }

    public ConsultationEntity toEntity(Consultation consultation) {
        return new ConsultationEntity(
                consultation.getId(),
                consultation.getMedicId(),
                consultation.getPatientId(),
                consultation.getDate(),
                consultation.getNotes(),
                consultation.getPrescription(),
                consultation.getStatus()
        );
    }
}
