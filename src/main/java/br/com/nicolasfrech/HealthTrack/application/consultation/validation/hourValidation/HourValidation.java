package br.com.nicolasfrech.HealthTrack.application.consultation.validation.hourValidation;

import br.com.nicolasfrech.HealthTrack.domain.consultation.Consultation;

import java.time.LocalDateTime;

public interface HourValidation {
    void validate(Consultation consultation, LocalDateTime date);
}
