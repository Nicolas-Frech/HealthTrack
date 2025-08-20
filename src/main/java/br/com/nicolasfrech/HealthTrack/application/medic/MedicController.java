package br.com.nicolasfrech.HealthTrack.application.medic;

import br.com.nicolasfrech.HealthTrack.application.medic.dto.MedicRegistDTO;
import br.com.nicolasfrech.HealthTrack.application.medic.dto.MedicReturnDTO;
import br.com.nicolasfrech.HealthTrack.application.medic.dto.MedicUpdateDTO;
import br.com.nicolasfrech.HealthTrack.domain.medic.Medic;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/medic")
public class MedicController {

    @Autowired
    private MedicService medicService;

    @PostMapping
    @Transactional
    public ResponseEntity<MedicReturnDTO> registMedic(@RequestBody @Valid MedicRegistDTO dto, UriComponentsBuilder uriBuilder) {
        Medic medic = medicService.registMedic(dto);
        URI uri = uriBuilder.path("/medic/{id}").buildAndExpand(medic.getId()).toUri();

        return ResponseEntity.created(uri).body(new MedicReturnDTO(medic));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> deleteMedic(@PathVariable Long id) {
        medicService.deleteMedic(id);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<MedicReturnDTO> findMedicById(@PathVariable Long id) {
        Medic medic = medicService.findMedicById(id);

        return ResponseEntity.ok(new MedicReturnDTO(medic));
    }

    @PutMapping
    @Transactional
    public ResponseEntity<MedicReturnDTO> updateMedic(@RequestBody @Valid MedicUpdateDTO dto) {
        Medic medic = medicService.updateMedic(dto);

        return ResponseEntity.ok(new MedicReturnDTO(medic));
    }
}
