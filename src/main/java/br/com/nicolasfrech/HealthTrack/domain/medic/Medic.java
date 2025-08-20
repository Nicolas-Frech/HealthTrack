package br.com.nicolasfrech.HealthTrack.domain.medic;

import br.com.nicolasfrech.HealthTrack.application.medic.dto.MedicUpdateDTO;
import br.com.nicolasfrech.HealthTrack.domain.validation.DomainValidator;

public class Medic {

    private Long id;
    private String name;
    private String crm;
    private Speciality speciality;
    private String telephone;
    private String email;
    private Boolean active;

    public Medic() {
    }

    public Medic(Long id, String name, String crm, Speciality speciality, String telephone, String email, Boolean active) {
        this.id = id;
        this.name = name;
        this.crm = DomainValidator.validateCRM(crm);
        this.speciality = speciality;
        this.telephone = DomainValidator.validateTelephone(telephone);
        this.email = DomainValidator.validateEmail(email);
        this.active = active;
    }

    public Medic(String name, String crm, Speciality speciality, String telephone, String email) {
        this.name = name;
        this.crm = DomainValidator.validateCRM(crm);
        this.speciality = speciality;
        this.telephone = DomainValidator.validateTelephone(telephone);
        this.email = DomainValidator.validateEmail(email);
        this.active = true;
    }

    public void deleteMedic() {
        this.active = false;
    }

    public void updateMedic(MedicUpdateDTO dto) {
        if(dto.email() != null && !dto.email().isBlank()) {
            this.email = DomainValidator.validateEmail(dto.email());
        }
        if(dto.telephone() != null && !dto.telephone().isBlank()) {
            this.telephone = DomainValidator.validateTelephone(dto.telephone());
        }
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
