package br.com.nicolasfrech.HealthTrack.infra.consultation.gateway;

import br.com.nicolasfrech.HealthTrack.application.consultation.gateway.ConsultationRepository;
import br.com.nicolasfrech.HealthTrack.domain.consultation.Consultation;
import br.com.nicolasfrech.HealthTrack.infra.consultation.persistence.ConsultationEntity;
import br.com.nicolasfrech.HealthTrack.infra.consultation.persistence.ConsultationRepositoryJPA;

public class ConsultationRepositoryImpl implements ConsultationRepository {

    private final ConsultationRepositoryJPA jpaRepository;
    private final ConsultationEntityMapper mapper;

    public ConsultationRepositoryImpl(ConsultationRepositoryJPA jpaRepository, ConsultationEntityMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public Consultation save(Consultation consultation) {
        ConsultationEntity entity = mapper.toEntity(consultation);
        jpaRepository.save(entity);

        return mapper.toDomain(entity);
    }
}
