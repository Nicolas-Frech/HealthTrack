package br.com.nicolasfrech.HealthTrack.application.medic.validation;

import br.com.nicolasfrech.HealthTrack.application.medic.dto.MedicRegistDTO;
import br.com.nicolasfrech.HealthTrack.application.medic.gateway.MedicRepository;
import br.com.nicolasfrech.HealthTrack.domain.medic.Speciality;
import br.com.nicolasfrech.HealthTrack.infra.exception.ValidateException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

public class ValidateCRMTest {

    @Mock
    private MedicRepository medicRepository;

    private ValidateCRM validateCRM;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        validateCRM = new ValidateCRM(medicRepository);
    }

    @Test
    @DisplayName("Should pass when CRM does not exist")
    void validateCRMScenario01() {
        MedicRegistDTO dto = new MedicRegistDTO("name", "SC123456", Speciality.CARDIOLOGIA,
                "(12) 02928-2029", "email@email.com");

        when(medicRepository.existsByCrm(dto.crm())).thenReturn(false);

        assertDoesNotThrow(() -> validateCRM.validate(dto));
    }

    @Test
    @DisplayName("Should not pass when CRM exists")
    void validateCRMScenario02() {
        MedicRegistDTO dto = new MedicRegistDTO("name", "SC123456", Speciality.CARDIOLOGIA,
                "(12) 02928-2029", "email@email.com");

        when(medicRepository.existsByCrm(dto.crm())).thenReturn(true);

        assertThrows(ValidateException.class, () -> validateCRM.validate(dto));
    }
}
