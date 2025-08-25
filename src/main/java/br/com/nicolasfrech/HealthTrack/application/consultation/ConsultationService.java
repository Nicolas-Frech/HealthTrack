package br.com.nicolasfrech.HealthTrack.application.consultation;

import br.com.nicolasfrech.HealthTrack.application.consultation.dto.BookConsultationDTO;
import br.com.nicolasfrech.HealthTrack.application.consultation.dto.ChangeDateDTO;
import br.com.nicolasfrech.HealthTrack.application.consultation.dto.ConsultationNotesDTO;
import br.com.nicolasfrech.HealthTrack.application.consultation.dto.UpdateStatusDTO;
import br.com.nicolasfrech.HealthTrack.application.consultation.gateway.ConsultationRepository;
import br.com.nicolasfrech.HealthTrack.application.consultation.validation.bookConsultation.BookConsultationValidation;
import br.com.nicolasfrech.HealthTrack.application.consultation.validation.hourValidation.HourValidation;
import br.com.nicolasfrech.HealthTrack.application.consultation.validation.updateStatus.UpdateStatusValidation;
import br.com.nicolasfrech.HealthTrack.application.medic.gateway.MedicRepository;
import br.com.nicolasfrech.HealthTrack.application.patient.gateway.PatientRepository;
import br.com.nicolasfrech.HealthTrack.domain.consultation.Consultation;
import br.com.nicolasfrech.HealthTrack.domain.consultation.ConsultationStatus;
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

    @Autowired
    private List<HourValidation> hourValidations;

    @Autowired
    private List<UpdateStatusValidation> updateStatusValidations;

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
        hourValidations.forEach(v -> v.validate(consultation, dto.date()));

        consultationRepository.save(consultation);
        return consultation;
    }

    public Consultation updateConsultationStatus(Long id, UpdateStatusDTO status) {
        Consultation consultation = consultationRepository.getReferenceById(id);
        updateStatusValidations.forEach(v -> v.validate(consultation, status));
        consultation.updateStatus(status.status());

        consultationRepository.save(consultation);
        return consultation;
    }

    public Consultation changeConsultationDate(Long id, ChangeDateDTO date) {
        Consultation consultation = consultationRepository.getReferenceById(id);
        hourValidations.forEach(v -> v.validate(consultation, date.date()));

        consultation.changeDate(date.date());

        consultationRepository.save(consultation);
        return consultation;
    }

    public Consultation addConsultationNotes(Long id, ConsultationNotesDTO dto) {
        Consultation consultation = consultationRepository.getReferenceById(id);

        consultation.addNotes(dto.notes());
        consultation.addPrescription(dto.prescription());

        consultationRepository.save(consultation);
        return consultation;
    }
}
