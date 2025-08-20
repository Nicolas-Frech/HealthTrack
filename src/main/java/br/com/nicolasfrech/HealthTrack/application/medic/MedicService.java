package br.com.nicolasfrech.HealthTrack.application.medic;

import br.com.nicolasfrech.HealthTrack.application.medic.dto.MedicRegistDTO;
import br.com.nicolasfrech.HealthTrack.application.medic.dto.MedicUpdateDTO;
import br.com.nicolasfrech.HealthTrack.application.medic.gateway.MedicRepository;
import br.com.nicolasfrech.HealthTrack.domain.medic.Medic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

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

    public void deleteMedic(Long id) {
        Medic medic = medicRepository.findByIdAndActiveTrue(id);

        medic.deleteMedic();
        medicRepository.save(medic);
    }

    public Medic findMedicById(Long id) {
        return medicRepository.findById(id);
    }

    public Medic updateMedic(MedicUpdateDTO dto) {
        Medic medic = medicRepository.findByIdAndActiveTrue(dto.id());
        medic.updateMedic(dto);

        medicRepository.save(medic);
        return medic;
    }

    public Page<Medic> listAllMedics(Pageable pageable) {
        return medicRepository.findAllByActiveTrue(pageable);
    }
}
