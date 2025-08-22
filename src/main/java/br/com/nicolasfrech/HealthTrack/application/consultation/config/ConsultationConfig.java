package br.com.nicolasfrech.HealthTrack.application.consultation.config;

import br.com.nicolasfrech.HealthTrack.infra.consultation.gateway.ConsultationEntityMapper;
import br.com.nicolasfrech.HealthTrack.infra.consultation.gateway.ConsultationRepositoryImpl;
import br.com.nicolasfrech.HealthTrack.infra.consultation.persistence.ConsultationRepositoryJPA;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConsultationConfig {

    @Bean
    ConsultationRepositoryImpl createConsultationRepositoryImpl(ConsultationRepositoryJPA jpa, ConsultationEntityMapper mapper) {
        return new ConsultationRepositoryImpl(jpa, mapper);
    }

    @Bean
    ConsultationEntityMapper createConsultationEntityMapper() {
        return new ConsultationEntityMapper();
    }
}
