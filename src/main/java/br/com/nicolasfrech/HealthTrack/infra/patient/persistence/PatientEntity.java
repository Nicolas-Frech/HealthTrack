package br.com.nicolasfrech.HealthTrack.infra.patient.persistence;


import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "patients")
@EqualsAndHashCode(of = "id")
public class PatientEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String cpf;
    private Integer age;
    private String email;
    private String telephone;
    private Boolean active;

    public PatientEntity(){
    }

    public PatientEntity(String telephone, String email, Integer age, String cpf, String name, Long id, Boolean active) {
        this.telephone = telephone;
        this.email = email;
        this.age = age;
        this.cpf = cpf;
        this.name = name;
        this.id = id;
        this.active = active;
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
