package br.com.nicolasfrech.HealthTrack.application.medic;

import br.com.nicolasfrech.HealthTrack.application.medic.dto.MedicRegistDTO;
import br.com.nicolasfrech.HealthTrack.application.medic.dto.MedicUpdateDTO;
import br.com.nicolasfrech.HealthTrack.application.medic.gateway.MedicRepository;
import br.com.nicolasfrech.HealthTrack.application.medic.validation.RegistValidation;
import br.com.nicolasfrech.HealthTrack.domain.medic.Medic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicService {

    private final MedicRepository medicRepository;

    @Autowired
    private List<RegistValidation> registerValidations;

    public MedicService(MedicRepository medicRepository) {
        this.medicRepository = medicRepository;
    }

    public Medic registMedic(MedicRegistDTO dto) {
        registerValidations.forEach(v -> v.validate(dto));

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
