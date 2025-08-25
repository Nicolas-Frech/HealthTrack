package br.com.nicolasfrech.HealthTrack.application.consultation.validation.updateStatus;

import br.com.nicolasfrech.HealthTrack.application.consultation.dto.UpdateStatusDTO;
import br.com.nicolasfrech.HealthTrack.domain.consultation.Consultation;
import br.com.nicolasfrech.HealthTrack.domain.consultation.ConsultationStatus;
import br.com.nicolasfrech.HealthTrack.infra.exception.ValidateException;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ValidateNoShowStatus implements UpdateStatusValidation {

    @Override
    public void validate(Consultation consultation, UpdateStatusDTO dto) {
        if (dto.status() == ConsultationStatus.NO_SHOW && consultation.getDate().isAfter(LocalDateTime.now())) {
            throw new ValidateException("Só é possível registrar falta após o horário da consulta!");
        }
    }
}
