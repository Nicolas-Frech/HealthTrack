package br.com.nicolasfrech.HealthTrack.application.medic.config;

import br.com.nicolasfrech.HealthTrack.infra.medic.gateway.MedicEntityMapper;
import br.com.nicolasfrech.HealthTrack.infra.medic.gateway.MedicRepositoryImpl;
import br.com.nicolasfrech.HealthTrack.infra.medic.persistence.MedicRepositoryJPA;
import br.com.nicolasfrech.HealthTrack.infra.user.gateway.UserEntityMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MedicConfig {

    @Bean
    MedicRepositoryImpl createMedicRepositoryImpl(MedicRepositoryJPA jpa, MedicEntityMapper mapper, UserEntityMapper userMapper) {
        return new MedicRepositoryImpl(jpa, mapper, userMapper);
    }

    @Bean
    MedicEntityMapper createMedicEntityMapper() {
        return new MedicEntityMapper();
    }
}
