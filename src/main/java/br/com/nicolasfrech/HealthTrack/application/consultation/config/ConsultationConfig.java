package br.com.nicolasfrech.HealthTrack.application.consultation.config;

import br.com.nicolasfrech.HealthTrack.infra.consultation.gateway.ConsultationRepositoryImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConsultationConfig {

    @Bean
    ConsultationRepositoryImpl createConsultationRepositoryImpl() {
        return new ConsultationRepositoryImpl();
    }
}
