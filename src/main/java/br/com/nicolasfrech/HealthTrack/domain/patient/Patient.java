package br.com.nicolasfrech.HealthTrack.domain.patient;

import br.com.nicolasfrech.HealthTrack.application.patient.dto.PatientUpdateDTO;
import br.com.nicolasfrech.HealthTrack.domain.validation.DomainValidator;

public class Patient {

    private Long id;
    private String name;
    private String cpf;
    private Integer age;
    private String email;
    private String telephone;
    private Boolean active;

    public Patient() {
    }

    public Patient(String name, String cpf, Integer age, String email, String telephone) {
        this.name = name;
        this.cpf = DomainValidator.validateCPF(cpf);
        this.age = age;
        this.email = DomainValidator.validateEmail(email);
        this.telephone = DomainValidator.validateTelephone(telephone);
        this.active = true;
    }

    public Patient(Long id, String telephone, String name, String cpf, Integer age, String email, Boolean active) {
        this.id = id;
        this.telephone = DomainValidator.validateTelephone(telephone);
        this.name = name;
        this.cpf = DomainValidator.validateCPF(cpf);
        this.age = age;
        this.email = DomainValidator.validateEmail(email);
        this.active = active;
    }

    public void deletePatient() {
        this.active = false;
    }

    public void updatePatient(PatientUpdateDTO dto) {
        if(dto.email() != null && !dto.email().isBlank()) {
            this.email = DomainValidator.validateEmail(dto.email());
        }
        if(dto.age() != null && dto.age() > 0) {
            this.age = dto.age();
        }
        if(dto.telephone() != null && !dto.telephone().isBlank()) {
            this.telephone = DomainValidator.validateTelephone(dto.telephone());
        }
    }

    public Boolean getActive() {
        return active;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCpf() {
        return cpf;
    }

    public Integer getAge() {
        return age;
    }

    public String getEmail() {
        return email;
    }

    public String getTelephone() {
        return telephone;
    }
}
