package br.com.nicolasfrech.HealthTrack.application.medic;

import br.com.nicolasfrech.HealthTrack.application.medic.dto.MedicRegistDTO;
import br.com.nicolasfrech.HealthTrack.application.medic.gateway.MedicRepository;
import br.com.nicolasfrech.HealthTrack.domain.medic.Medic;
import org.springframework.stereotype.Service;

@Service
public class MedicService {

    private final MedicRepository medicRepository;

    public MedicService(MedicRepository medicRepository) {
        this.medicRepository = medicRepository;
    }

    public Medic registMedic(MedicRegistDTO dto) {
        Medic medic = new Medic(dto.name(), dto.crm(), dto.speciality(), dto.telephone(),
                dto.email());

        medicRepository.save(medic);
        return medic;
    }
}
