package br.com.nicolasfrech.HealthTrack.application.consultation;

import br.com.nicolasfrech.HealthTrack.application.consultation.dto.BookConsultationDTO;
import br.com.nicolasfrech.HealthTrack.application.consultation.gateway.ConsultationRepository;
import br.com.nicolasfrech.HealthTrack.application.consultation.validation.BookConsultationValidation;
import br.com.nicolasfrech.HealthTrack.application.medic.gateway.MedicRepository;
import br.com.nicolasfrech.HealthTrack.application.patient.gateway.PatientRepository;
import br.com.nicolasfrech.HealthTrack.domain.consultation.Consultation;
import br.com.nicolasfrech.HealthTrack.domain.medic.Medic;
import br.com.nicolasfrech.HealthTrack.domain.patient.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsultationService {

    private final ConsultationRepository consultationRepository;
    private final MedicRepository medicRepository;
    private final PatientRepository patientRepository;

    @Autowired
    private List<BookConsultationValidation> bookConsultationValidations;

    public ConsultationService(ConsultationRepository consultationRepository, MedicRepository medicRepository, PatientRepository patientRepository) {
        this.consultationRepository = consultationRepository;
        this.medicRepository = medicRepository;
        this.patientRepository = patientRepository;
    }

    public Consultation bookConsultation(BookConsultationDTO dto) {
        bookConsultationValidations.forEach(v -> v.validate(dto));

        Medic medic = medicRepository.findByCrmAndActiveTrue(dto.medicCRM());
        Patient patient = patientRepository.findByCpfAndActiveTrue(dto.patientCPF());

        Consultation consultation = new Consultation(medic.getId(), patient.getId(), dto.date());

        consultationRepository.save(consultation);
        return consultation;
    }
}
