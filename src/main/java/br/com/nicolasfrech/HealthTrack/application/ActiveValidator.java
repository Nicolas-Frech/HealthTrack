package br.com.nicolasfrech.HealthTrack.application;

import br.com.nicolasfrech.HealthTrack.application.consultation.gateway.ConsultationRepository;
import br.com.nicolasfrech.HealthTrack.application.medic.gateway.MedicRepository;
import br.com.nicolasfrech.HealthTrack.application.patient.gateway.PatientRepository;
import br.com.nicolasfrech.HealthTrack.infra.exception.ValidateException;

public class ActiveValidator {

    private final MedicRepository medicRepository;
    private final PatientRepository patientRepository;
    private final ConsultationRepository consultationRepository;

    public ActiveValidator(MedicRepository medicRepository, PatientRepository patientRepository, ConsultationRepository consultationRepository) {
        this.medicRepository = medicRepository;
        this.patientRepository = patientRepository;
        this.consultationRepository = consultationRepository;
    }

    public void validateMedicActive(Long id) {
        if(!medicRepository.existsByIdAndActiveTrue(id)) {
            throw new ValidateException("Não existe médico com esse ID!");
        }
    }

    public void validatePatientActive(Long id) {
        if(!patientRepository.existsByIdAndActiveTrue(id)) {
            throw new ValidateException("Não existe paciente com esse ID!");
        }
    }

    public void validateConsultation(Long id) {
        if(!consultationRepository.existsById(id)) {
            throw new ValidateException("Não existe consulta com esse ID!");
        }
    }
}
