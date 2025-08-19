package br.com.nicolasfrech.HealthTrack.application.patient;

import br.com.nicolasfrech.HealthTrack.application.patient.dto.PatientRegistDTO;
import br.com.nicolasfrech.HealthTrack.application.patient.dto.PatientReturnDTO;
import br.com.nicolasfrech.HealthTrack.application.patient.dto.PatientUpdateDTO;
import br.com.nicolasfrech.HealthTrack.domain.patient.Patient;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
    public ResponseEntity<PatientReturnDTO> findPatientById(@PathVariable Long id) {
        Patient patient = patientService.findPatientById(id);

        return ResponseEntity.ok().body(new PatientReturnDTO(patient));
    }

    @PutMapping
    @Transactional
    public ResponseEntity<PatientReturnDTO> updatePatient(@RequestBody @Valid PatientUpdateDTO dto) {
        Patient patient = patientService.updatePatient(dto);

        return ResponseEntity.ok().body(new PatientReturnDTO(patient));
    }

    @GetMapping
    public ResponseEntity<Page<PatientReturnDTO>> listAllPatients(@PageableDefault(size = 10, sort = {"name"}) Pageable pageable) {
        Page<PatientReturnDTO> page = patientService.findAllPatients(pageable).map(PatientReturnDTO::new);
        return ResponseEntity.ok(page);
    }
}
