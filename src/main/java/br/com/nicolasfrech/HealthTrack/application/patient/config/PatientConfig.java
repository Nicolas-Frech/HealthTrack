package br.com.nicolasfrech.HealthTrack.application.patient.config;

import br.com.nicolasfrech.HealthTrack.infra.patient.gateway.PatientEntityMapper;
import br.com.nicolasfrech.HealthTrack.infra.patient.gateway.PatientRepositoryImpl;
import br.com.nicolasfrech.HealthTrack.infra.patient.persistence.PatientRepositoryJPA;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PatientConfig {

    @Bean
    PatientRepositoryImpl createPatientRepositoryImpl(PatientRepositoryJPA jpa, PatientEntityMapper mapper) {
        return new PatientRepositoryImpl(mapper, jpa);
    }

    @Bean
    PatientEntityMapper createPatientEntityMapper() {
        return new PatientEntityMapper();
    }
}
