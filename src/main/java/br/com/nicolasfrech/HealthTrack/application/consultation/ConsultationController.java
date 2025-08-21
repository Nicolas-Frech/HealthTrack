package br.com.nicolasfrech.HealthTrack.application.consultation;


import br.com.nicolasfrech.HealthTrack.application.consultation.dto.BookConsultationDTO;
import br.com.nicolasfrech.HealthTrack.application.consultation.dto.ConsultationReturnDTO;
import br.com.nicolasfrech.HealthTrack.domain.consultation.Consultation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
}
