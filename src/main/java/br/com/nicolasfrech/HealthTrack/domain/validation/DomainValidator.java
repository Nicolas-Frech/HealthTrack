package br.com.nicolasfrech.HealthTrack.domain.validation;

public class DomainValidator {
    public static String validateCPF(String cpf) {
        if (cpf == null || !cpf.matches("^\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}$|^\\d{11}$")) {
            throw new IllegalArgumentException("CPF INVÁLIDO!");
        }
        return cpf;
    }

    public static String validateEmail(String email) {
        if (email == null || !email.matches("^[\\w.-]+@[\\w.-]+\\.\\w{2,}$")) {
            throw new IllegalArgumentException("E-MAIL INVÁLIDO!");
        }
        return email;
    }

    public static String validateTelephone(String telephone) {
        if (telephone == null || !telephone.matches("^\\(\\d{2}\\)\\s?\\d{4,5}-\\d{4}$")) {
            throw new IllegalArgumentException("TELEFONE INVÁLIDO!");
        }
        return telephone;
    }

    public static String validateCRM(String crm) {
        if (crm == null || !crm.matches("^[A-Z]{2}\\d{4,6}$")) {
            throw new IllegalArgumentException("CRM INVÁLIDO!");
        }
        return crm;
    }
}
