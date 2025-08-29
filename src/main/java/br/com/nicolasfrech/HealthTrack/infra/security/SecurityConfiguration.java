package br.com.nicolasfrech.HealthTrack.infra.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Autowired
    private SecurityFilter securityFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.csrf(csrf -> csrf.disable())
                .cors(Customizer.withDefaults())
                .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(req -> req
                        .requestMatchers("/auth/**").permitAll()

                        // ADMIN
                        .requestMatchers(HttpMethod.POST, "/consultation").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/consultation/*/status").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/consultation/*/date").hasRole("ADMIN")

                        // MEDIC
                        .requestMatchers(HttpMethod.PUT, "/consultation/*/notes").hasRole("MEDIC")
                        .requestMatchers(HttpMethod.GET, "/consultation/medic/*").hasRole("MEDIC")

                        .requestMatchers("/consultation/**", "/medic/**").hasAnyRole("ADMIN", "MEDIC")
                        .requestMatchers("/patient/**").hasAnyRole("ADMIN", "MEDIC")).addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }
}
