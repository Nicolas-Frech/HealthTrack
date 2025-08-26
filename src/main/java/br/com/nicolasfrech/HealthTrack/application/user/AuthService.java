package br.com.nicolasfrech.HealthTrack.application.user;

import br.com.nicolasfrech.HealthTrack.application.user.dto.UserRegistDTO;
import br.com.nicolasfrech.HealthTrack.application.user.gateway.UserRepository;
import br.com.nicolasfrech.HealthTrack.domain.user.Role;
import br.com.nicolasfrech.HealthTrack.domain.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User registUser(UserRegistDTO dto) {
        String encodedPwd = passwordEncoder.encode(dto.password());
        User user = new User(dto.username(), encodedPwd, Role.MEDIC);

        return userRepository.save(user);
    }
}
