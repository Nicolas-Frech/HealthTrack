package br.com.nicolasfrech.HealthTrack.infra.medic.persistence;

import br.com.nicolasfrech.HealthTrack.domain.medic.Speciality;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "medics")
@EqualsAndHashCode(of = "id")
public class MedicEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String crm;

    @Enumerated(EnumType.STRING)
    private Speciality speciality;

    private String telephone;
    private String email;
    private Boolean active;

    public MedicEntity() {
    }

    public MedicEntity(Long id, String name, String crm, Speciality speciality, String telephone, String email, Boolean active) {
        this.id = id;
        this.name = name;
        this.crm = crm;
        this.speciality = speciality;
        this.telephone = telephone;
        this.email = email;
        this.active = active;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCrm() {
        return crm;
    }

    public Speciality getSpeciality() {
        return speciality;
    }

    public String getTelephone() {
        return telephone;
    }

    public String getEmail() {
        return email;
    }

    public Boolean getActive() {
        return active;
    }
}
