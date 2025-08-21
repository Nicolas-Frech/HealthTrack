package br.com.nicolasfrech.HealthTrack.application.patient.validation;

import br.com.nicolasfrech.HealthTrack.application.patient.dto.PatientRegistDTO;
import br.com.nicolasfrech.HealthTrack.application.patient.gateway.PatientRepository;
import br.com.nicolasfrech.HealthTrack.infra.exception.ValidateException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

public class ValidateCPFTest {

    @Mock
    private PatientRepository patientRepository;

    private ValidateCPF validateCPF;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        validateCPF = new ValidateCPF(patientRepository);
    }

    @Test
    @DisplayName("Should pass when CPF does not exist")
    public void validateCPFScenario01() {
        PatientRegistDTO dto = new PatientRegistDTO("name", "063.837.709-93", 14,
                "email@email.com", "(47) 99999-3938");

        when(patientRepository.existsByCpf(dto.cpf())).thenReturn(false);

        assertDoesNotThrow(() -> validateCPF.validate(dto));
    }

    @Test
    @DisplayName("Should not pass when CPF exists")
    public void validateCPFScenario02() {
        PatientRegistDTO dto = new PatientRegistDTO("name", "063.837.709-93", 14,
                "email@email.com", "(47) 99999-3938");

        when(patientRepository.existsByCpf(dto.cpf())).thenReturn(true);

        assertThrows(ValidateException.class, () -> validateCPF.validate(dto));
    }
}
