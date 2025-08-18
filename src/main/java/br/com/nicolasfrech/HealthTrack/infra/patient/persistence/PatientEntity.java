package br.com.nicolasfrech.HealthTrack.infra.patient.persistence;


import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "patients")
@Getter
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
public class PatientEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String cpf;
    private Integer age;
    private String email;
    private String telephone;

    public PatientEntity(String telephone, String email, Integer age, String cpf, String name, Long id) {
        this.telephone = telephone;
        this.email = email;
        this.age = age;
        this.cpf = cpf;
        this.name = name;
        this.id = id;
    }
}
