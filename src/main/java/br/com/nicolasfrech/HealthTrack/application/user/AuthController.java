package br.com.nicolasfrech.HealthTrack.application.user;

import br.com.nicolasfrech.HealthTrack.application.user.dto.UserRegistDTO;
import br.com.nicolasfrech.HealthTrack.application.user.dto.UserReturnDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @PostMapping
    @Transactional
    public ResponseEntity<UserReturnDTO> registUser(@RequestBody @Valid UserRegistDTO dto) {
        return null;
    }
}
