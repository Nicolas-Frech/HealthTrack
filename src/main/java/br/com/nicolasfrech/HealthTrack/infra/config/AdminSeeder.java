package br.com.nicolasfrech.HealthTrack.infra.config;

import br.com.nicolasfrech.HealthTrack.application.user.gateway.UserRepository;
import br.com.nicolasfrech.HealthTrack.domain.user.Role;
import br.com.nicolasfrech.HealthTrack.domain.user.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class AdminSeeder {

    @Value("${app.admin.username}")
    private String adminUsername;

    @Value("${app.admin.password}")
    private String adminPassword;

    @Bean
    CommandLineRunner initAdmin(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            if (!userRepository.existsByRole(Role.ADMIN)) {
                User admin = new User(
                        adminUsername,
                        passwordEncoder.encode(adminPassword),
                        Role.ADMIN
                );
                userRepository.save(admin);
                System.out.println("✅ Usuário ADMIN criado automaticamente: " + adminUsername);
            }
        };
    }
}
