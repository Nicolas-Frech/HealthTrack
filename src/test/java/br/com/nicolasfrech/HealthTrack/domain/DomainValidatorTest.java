package br.com.nicolasfrech.HealthTrack.domain;

import br.com.nicolasfrech.HealthTrack.domain.validation.DomainValidator;
import br.com.nicolasfrech.HealthTrack.infra.exception.ValidateException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DomainValidatorTest {

    @Test
    @DisplayName("Telephone should pass")
    public void validateTelephoneScenario01() {
        assertDoesNotThrow(() -> DomainValidator.validateTelephone("(47) 99999-9090"));
    }

    @Test
    @DisplayName("Telephone should not pass")
    public void validateTelephoneScenario02() {
        assertThrows(ValidateException.class, () -> DomainValidator.validateTelephone("(47) 9-9090"));
    }

    @Test
    @DisplayName("CPF should pass")
    public void validateCPFScenario01() {
        assertDoesNotThrow(() -> DomainValidator.validateCPF("123.456.789-00"));
    }

    @Test
    @DisplayName("CPF should not pass")
    public void validateCPFScenario02() {
        assertThrows(ValidateException.class, () -> DomainValidator.validateCPF("123.456.78900"));
    }

    @Test
    @DisplayName("CRM should pass")
    public void validateCrmScenario01() {
        assertDoesNotThrow(() -> DomainValidator.validateCRM("SC123456"));
    }

    @Test
    @DisplayName("CRM should not pass")
    public void validateCRMScenario02() {
        assertThrows(ValidateException.class, () -> DomainValidator.validateCRM("SC12"));
    }

    @Test
    @DisplayName("Email should pass")
    public void validateEmailScenario01() {
        assertDoesNotThrow(() -> DomainValidator.validateEmail("email@email.com"));
    }

    @Test
    @DisplayName("Email should not pass")
    public void validateEmailScenario02() {
        assertThrows(ValidateException.class, () -> DomainValidator.validateEmail("emailemail.com"));
    }
}
