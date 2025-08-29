package br.com.nicolasfrech.HealthTrack.application.consultation;


import br.com.nicolasfrech.HealthTrack.application.consultation.dto.*;
import br.com.nicolasfrech.HealthTrack.domain.consultation.Consultation;
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
@RequestMapping("/consultation")
public class ConsultationController {

    @Autowired
    private ConsultationService consultationService;

    @PostMapping
    @Transactional
    public ResponseEntity<ConsultationReturnDTO> bookConsultation(@RequestBody @Valid BookConsultationDTO dto, UriComponentsBuilder uriBuilder) {
        Consultation consultation = consultationService.bookConsultation(dto);

        URI uri = uriBuilder.path("/consultation/{id}").buildAndExpand(consultation.getId()).toUri();

        return ResponseEntity.created(uri).body(new ConsultationReturnDTO(consultation));
    }

    @PutMapping("/{id}/status")
    @Transactional
    public ResponseEntity<ConsultationReturnDTO> updateConsultationStatus(@PathVariable Long id, @RequestBody @Valid UpdateStatusDTO status) {
        Consultation consultation = consultationService.updateConsultationStatus(id, status);

        return ResponseEntity.ok(new ConsultationReturnDTO(consultation));
    }

    @PutMapping("/{id}/date")
    @Transactional
    public ResponseEntity<ConsultationReturnDTO> changeConsultationDate(@PathVariable Long id, @RequestBody @Valid ChangeDateDTO date) {
        Consultation consultation = consultationService.changeConsultationDate(id, date);

        return ResponseEntity.ok(new ConsultationReturnDTO(consultation));
    }

    @PutMapping("/{id}/notes")
    @Transactional
    public ResponseEntity<ConsultationReturnDTO> addConsultationNotes(@PathVariable Long id, @RequestBody ConsultationNotesDTO dto) {
        Consultation consultation = consultationService.addConsultationNotes(id, dto);

        return ResponseEntity.ok(new ConsultationReturnDTO(consultation));
    }

    @GetMapping
    public ResponseEntity<Page<ConsultationReturnDTO>> findAllConsultations(@PageableDefault(size = 10, sort = {"date"}) Pageable pageable) {
        Page<ConsultationReturnDTO> page = consultationService.findAllConsultations(pageable)
                .map(ConsultationReturnDTO::new);

        return ResponseEntity.ok(page);
    }

    @GetMapping("/medic/{id}")
    public ResponseEntity<Page<ConsultationReturnDTO>> findAllByMedicId(
            @PageableDefault(size = 10, sort = {"date"}) Pageable pageable,
            @PathVariable Long id) {
        Page<ConsultationReturnDTO> page = consultationService.findAllByMedicId(pageable, id)
                .map(ConsultationReturnDTO::new);

        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConsultationReturnDTO> findById(@PathVariable Long id) {
        Consultation consultation = consultationService.findById(id);

        return ResponseEntity.ok(new ConsultationReturnDTO(consultation));
    }
}
