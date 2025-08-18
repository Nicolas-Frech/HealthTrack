package br.com.nicolasfrech.HealthTrack.domain;

public class Patient {

    private Long id;
    private String name;
    private String cpf;
    private Integer age;
    private String email;
    private String telephone;

    public Patient() {
    }

    public Patient(Long id, String telephone, String name, String cpf, Integer age, String email) {
        this.id = id;
        this.telephone = telephone;
        this.name = name;
        this.cpf = cpf;
        this.age = age;
        this.email = email;
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
