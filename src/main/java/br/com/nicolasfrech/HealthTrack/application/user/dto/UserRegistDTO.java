package br.com.nicolasfrech.HealthTrack.application.user.dto;

import jakarta.validation.constraints.NotNull;

public record UserRegistDTO(
        @NotNull
        String username,
        @NotNull
        String password) {
}
