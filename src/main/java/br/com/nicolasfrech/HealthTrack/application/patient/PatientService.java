package br.com.nicolasfrech.HealthTrack.application.patient;

import br.com.nicolasfrech.HealthTrack.application.patient.dto.PatientRegistDTO;
import br.com.nicolasfrech.HealthTrack.application.patient.dto.PatientUpdateDTO;
import br.com.nicolasfrech.HealthTrack.application.patient.gateway.PatientRepository;
import br.com.nicolasfrech.HealthTrack.application.patient.validation.PatientRegistValidation;
import br.com.nicolasfrech.HealthTrack.domain.patient.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService {

    private final PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Autowired
    private List<PatientRegistValidation> registerValidations;

    public Patient registPatient(PatientRegistDTO dto) {
        registerValidations.forEach(v -> v.validate(dto));
        Patient patient = new Patient(dto.name(), dto.cpf(), dto.age(),
                dto.email(), dto.telephone());

        patientRepository.save(patient);
        return patient;
    }

    public void deletePatient(Long id) {
        Patient patient = patientRepository.findByIdAndActiveTrue(id);
        patient.deletePatient();

        patientRepository.save(patient);
    }

    public Patient findPatientById(Long id) {
        Patient patient = patientRepository.findById(id);

        return patient;
    }

    public Patient updatePatient(PatientUpdateDTO dto) {
        Patient patient = patientRepository.findById(dto.id());
        patient.updatePatient(dto);

        patientRepository.save(patient);
        return patient;
    }

    public Page<Patient> findAllPatients(Pageable pageable) {
        return patientRepository.findAllByActiveTrue(pageable);
    }
}
