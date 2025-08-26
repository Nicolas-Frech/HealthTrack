package br.com.nicolasfrech.HealthTrack.application.user.dto;

import br.com.nicolasfrech.HealthTrack.domain.user.Role;
import br.com.nicolasfrech.HealthTrack.domain.user.User;

public record UserReturnDTO(Long id, String username, String password, Role role) {
    public UserReturnDTO(User user) {
        this(user.getId(), user.getUsername(), user.getPassword(), user.getRole());
    }
}
