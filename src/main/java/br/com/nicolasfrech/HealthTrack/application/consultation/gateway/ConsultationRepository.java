package br.com.nicolasfrech.HealthTrack.application.consultation.gateway;

import br.com.nicolasfrech.HealthTrack.domain.consultation.Consultation;

public interface ConsultationRepository {

    Consultation save(Consultation consultation);
}
