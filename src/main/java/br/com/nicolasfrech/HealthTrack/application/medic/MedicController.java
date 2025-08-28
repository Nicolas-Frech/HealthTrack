package br.com.nicolasfrech.HealthTrack.application.medic;

import br.com.nicolasfrech.HealthTrack.application.medic.dto.MedicRegistDTO;
import br.com.nicolasfrech.HealthTrack.application.medic.dto.MedicReturnDTO;
import br.com.nicolasfrech.HealthTrack.application.medic.dto.MedicUpdateDTO;
import br.com.nicolasfrech.HealthTrack.domain.medic.Medic;
import br.com.nicolasfrech.HealthTrack.infra.user.persistence.UserEntity;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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

    @GetMapping
    public ResponseEntity<Page<MedicReturnDTO>> listAllMedics(@PageableDefault(size = 10, sort = {"name"}) Pageable pageable) {
        Page<MedicReturnDTO> page = medicService.listAllMedics(pageable).map(MedicReturnDTO::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/me")
    public ResponseEntity<MedicReturnDTO> getLoggedMedic(@AuthenticationPrincipal UserEntity user) {
        Medic medic = medicService.getLoggedMedic(user);

        return ResponseEntity.ok(new MedicReturnDTO(medic));
    }
}
