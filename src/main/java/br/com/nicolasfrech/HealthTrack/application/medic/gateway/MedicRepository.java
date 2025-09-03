package br.com.nicolasfrech.HealthTrack.application.medic.gateway;

import br.com.nicolasfrech.HealthTrack.domain.medic.Medic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MedicRepository {

    Medic save(Medic medic);

    Medic findByIdAndActiveTrue(Long id);

    Medic findById(Long id);

    Page<Medic> findAllByActiveTrue(Pageable pageable);

    boolean existsByCrm(String crm);

    boolean existsByTelephone(String telephone);

    Medic findByCrmAndActiveTrue(String crm);

    boolean existsByCrmAndActiveTrue(String crm);

    boolean existsByIdAndActiveTrue(Long id);
}
