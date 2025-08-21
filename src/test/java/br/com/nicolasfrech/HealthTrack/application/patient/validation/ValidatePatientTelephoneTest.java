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

public class ValidatePatientTelephoneTest {

    @Mock
    private PatientRepository patientRepository;

    private ValidatePatientTelephone validateTelephone;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        validateTelephone = new ValidatePatientTelephone(patientRepository);
    }

    @Test
    @DisplayName("Should pass when telephone does not exist")
    public void validateTelephoneScenario01() {
        PatientRegistDTO dto = new PatientRegistDTO("name", "063.837.709-93", 14,
                "email@email.com", "(47) 99999-3938");

        when(patientRepository.existsByTelephone(dto.telephone())).thenReturn(false);

        assertDoesNotThrow(() -> validateTelephone.validate(dto));
    }

    @Test
    @DisplayName("Should not pass when telephone exists")
    public void validateTelephoneScenario02() {
        PatientRegistDTO dto = new PatientRegistDTO("name", "063.837.709-93", 14,
                "email@email.com", "(47) 99999-3938");

        when(patientRepository.existsByTelephone(dto.telephone())).thenReturn(true);

        assertThrows(ValidateException.class, () -> validateTelephone.validate(dto));
    }
}
