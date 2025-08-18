package br.com.nicolasfrech.HealthTrack.domain.patient;

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

    public Patient(String name, String cpf, Integer age, String email, String telephone) {
        this.name = name;
        if(!cpf.matches("^\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}$|^\\d{11}$")) {
            throw new IllegalArgumentException("CPF INVÁLIDO!");
        }

        this.cpf = cpf;
        this.age = age;

        if(!email.matches("^[\\w.-]+@[\\w.-]+\\.\\w{2,}$")) {
            throw new IllegalArgumentException("E-MAIL INVÁLIDO!");
        }

        this.email = email;

        if(!telephone.matches("^\\(\\d{2}\\)\\s?\\d{4,5}-\\d{4}$")) {
            throw new IllegalArgumentException("TELEFONE INVÁLIDO!");
        }

        this.telephone = telephone;
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
