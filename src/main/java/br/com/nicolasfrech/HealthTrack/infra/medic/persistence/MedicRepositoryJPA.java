package br.com.nicolasfrech.HealthTrack.infra.medic.persistence;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicRepositoryJPA extends JpaRepository<MedicEntity, Long> {

    MedicEntity findByIdAndActiveTrue(Long id);

    Page<MedicEntity> findAllByActiveTrue(Pageable pageable);

    boolean existsByCrm(String crm);

    boolean existsByTelephone(String telephone);

    MedicEntity findByCrmAndActiveTrue(String crm);
}
