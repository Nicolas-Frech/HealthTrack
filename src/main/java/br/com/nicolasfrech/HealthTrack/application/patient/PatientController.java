package br.com.nicolasfrech.HealthTrack.application.patient;

import br.com.nicolasfrech.HealthTrack.application.patient.dto.PatientRegistDTO;
import br.com.nicolasfrech.HealthTrack.application.patient.dto.PatientReturnDTO;
import br.com.nicolasfrech.HealthTrack.domain.patient.Patient;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/patient")
public class PatientController {

    @Autowired
    private PatientService patientService;

    @PostMapping
    public ResponseEntity registPatient(@RequestBody @Valid PatientRegistDTO dto) {
        Patient patient = patientService.registPatient(dto);

        return ResponseEntity.ok().body(new PatientReturnDTO(patient));
    }
}
