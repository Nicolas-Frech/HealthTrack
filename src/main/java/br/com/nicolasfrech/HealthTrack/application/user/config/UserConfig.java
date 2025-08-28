package br.com.nicolasfrech.HealthTrack.application.user.config;

import br.com.nicolasfrech.HealthTrack.infra.medic.gateway.MedicEntityMapper;
import br.com.nicolasfrech.HealthTrack.infra.user.gateway.UserEntityMapper;
import br.com.nicolasfrech.HealthTrack.infra.user.gateway.UserRepositoryImpl;
import br.com.nicolasfrech.HealthTrack.infra.user.persistence.UserRepositoryJPA;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserConfig {

    @Bean
    UserRepositoryImpl createUserRepositoryImpl(UserEntityMapper mapper, MedicEntityMapper medicMapper, UserRepositoryJPA jpa) {
        return new UserRepositoryImpl(mapper, medicMapper, jpa);
    }

    @Bean
    UserEntityMapper createUserEntityMapper() {
        return new UserEntityMapper();
    }
}
