package br.com.nicolasfrech.HealthTrack.infra.consultation.gateway;

import br.com.nicolasfrech.HealthTrack.application.consultation.gateway.ConsultationRepository;
import br.com.nicolasfrech.HealthTrack.domain.consultation.Consultation;
import br.com.nicolasfrech.HealthTrack.domain.consultation.ConsultationStatus;
import br.com.nicolasfrech.HealthTrack.infra.consultation.persistence.ConsultationEntity;
import br.com.nicolasfrech.HealthTrack.infra.consultation.persistence.ConsultationRepositoryJPA;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;

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

    @Override
    public boolean existsByPatientIdAndDateAndStatus(Long id, LocalDateTime date, ConsultationStatus consultationStatus) {
        return jpaRepository.existsByPatientIdAndDateAndStatus(id, date, consultationStatus);
    }

    @Override
    public boolean existsByMedicIdAndDateAndStatus(Long id, LocalDateTime date, ConsultationStatus consultationStatus) {
        return jpaRepository.existsByMedicIdAndDateAndStatus(id, date, consultationStatus);
    }

    @Override
    public Consultation getReferenceById(Long id) {
        ConsultationEntity entity = jpaRepository.getReferenceById(id);

        return mapper.toDomain(entity);
    }

    @Override
    public Page<Consultation> findAll(Pageable pageable) {
        Page<ConsultationEntity> page = jpaRepository.findAll(pageable);
        return page.map(mapper::toDomain);
    }

    @Override
    public Page<Consultation> findAllByMedicId(Pageable pageable, Long id) {
        Page<ConsultationEntity> page = jpaRepository.findAllByMedicId(pageable, id);

        return page.map(mapper::toDomain);
    }
}
