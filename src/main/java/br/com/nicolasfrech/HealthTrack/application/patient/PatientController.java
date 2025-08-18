package br.com.nicolasfrech.HealthTrack.application.patient;

import br.com.nicolasfrech.HealthTrack.application.patient.dto.PatientRegistDTO;
import br.com.nicolasfrech.HealthTrack.application.patient.dto.PatientReturnDTO;
import br.com.nicolasfrech.HealthTrack.domain.patient.Patient;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/patient")
public class PatientController {

    @Autowired
    private PatientService patientService;

    @PostMapping
    @Transactional
    public ResponseEntity<PatientReturnDTO> registPatient(@RequestBody @Valid PatientRegistDTO dto,
                                                          UriComponentsBuilder uriBuilder) {
        Patient patient = patientService.registPatient(dto);

        URI uri = uriBuilder.path("/patient/{id}").buildAndExpand(patient.getId()).toUri();
        return ResponseEntity.created(uri).body(new PatientReturnDTO(patient));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deletePatient(@PathVariable Long id) {
        patientService.deletePatient(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity findPatientById(@PathVariable Long id) {
        Patient patient = patientService.findPatientById(id);

        return ResponseEntity.ok().body(new PatientReturnDTO(patient));
    }
}
