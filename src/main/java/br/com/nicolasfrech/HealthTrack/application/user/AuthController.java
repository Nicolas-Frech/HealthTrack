package br.com.nicolasfrech.HealthTrack.application.user;

import br.com.nicolasfrech.HealthTrack.application.user.dto.UserRegistDTO;
import br.com.nicolasfrech.HealthTrack.application.user.dto.UserReturnDTO;
import br.com.nicolasfrech.HealthTrack.domain.user.User;
import br.com.nicolasfrech.HealthTrack.infra.security.token.TokenDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping
    @Transactional
    public ResponseEntity<TokenDTO> login(@RequestBody @Valid UserRegistDTO dto) {
        String token = authService.login(dto);
        return ResponseEntity.ok(new TokenDTO(token));
    }
}
