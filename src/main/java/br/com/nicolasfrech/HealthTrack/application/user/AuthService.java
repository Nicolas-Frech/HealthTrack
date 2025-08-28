package br.com.nicolasfrech.HealthTrack.application.user;

import br.com.nicolasfrech.HealthTrack.application.user.dto.UserRegistDTO;
import br.com.nicolasfrech.HealthTrack.application.user.gateway.UserRepository;
import br.com.nicolasfrech.HealthTrack.domain.user.Role;
import br.com.nicolasfrech.HealthTrack.domain.user.User;
import br.com.nicolasfrech.HealthTrack.infra.security.token.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;
    private final UserRepository userRepository;

    public AuthService(AuthenticationManager authenticationManager, TokenService tokenService, UserRepository userRepository) {
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
        this.userRepository = userRepository;
    }

    public String login(UserRegistDTO dto) {
        var authToken = new UsernamePasswordAuthenticationToken(dto.username(), dto.password());
        authenticationManager.authenticate(authToken);

        String role;
        if (dto.username().equalsIgnoreCase("admin")) {
            role = "ADMIN";
        } else {
            role = "MEDIC";
        }

        return tokenService.createToken(dto.username(), role);
    }
}
