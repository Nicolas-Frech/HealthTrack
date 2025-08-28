package br.com.nicolasfrech.HealthTrack.application.medic;

import br.com.nicolasfrech.HealthTrack.application.medic.dto.MedicRegistDTO;
import br.com.nicolasfrech.HealthTrack.application.medic.dto.MedicUpdateDTO;
import br.com.nicolasfrech.HealthTrack.application.medic.gateway.MedicRepository;
import br.com.nicolasfrech.HealthTrack.application.medic.validation.MedicRegistValidation;
import br.com.nicolasfrech.HealthTrack.application.user.gateway.UserRepository;
import br.com.nicolasfrech.HealthTrack.domain.medic.Medic;
import br.com.nicolasfrech.HealthTrack.domain.user.Role;
import br.com.nicolasfrech.HealthTrack.domain.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicService {

    private final MedicRepository medicRepository;
    private final UserRepository userRepository;

    @Autowired
    private List<MedicRegistValidation> registerValidations;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public MedicService(MedicRepository medicRepository, UserRepository userRepository) {
        this.medicRepository = medicRepository;
        this.userRepository = userRepository;
    }

    public Medic registMedic(MedicRegistDTO dto) {
        registerValidations.forEach(v -> v.validate(dto));

        Medic medic = new Medic(dto.name(), dto.crm(), dto.speciality(), dto.telephone(),
                dto.email());

        String medicPassword = dto.crm().substring(0, 3) + dto.telephone().replaceAll("\\D", "").substring(0, 5);

        User user = new User(medic.getCrm(), passwordEncoder.encode(medicPassword), Role.MEDIC);

        medicRepository.save(medic);
        userRepository.save(user);
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
