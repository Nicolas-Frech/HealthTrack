package br.com.nicolasfrech.HealthTrack.application.patient;

import br.com.nicolasfrech.HealthTrack.application.patient.dto.PatientRegistDTO;
import br.com.nicolasfrech.HealthTrack.application.patient.gateway.PatientRepository;
import br.com.nicolasfrech.HealthTrack.domain.Patient;
import org.springframework.stereotype.Service;

@Service
public class PatientService {

    private final PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }


    public Patient registPatient(PatientRegistDTO dto) {
        Patient patient = new Patient(dto.name(), dto.cpf(), dto.age(),
                dto.email(), dto.telephone());

        patientRepository.save(patient);
        return patient;
    }
}
