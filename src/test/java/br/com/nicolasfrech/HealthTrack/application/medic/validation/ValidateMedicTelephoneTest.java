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

public class ValidateMedicTelephoneTest {

    @Mock
    private MedicRepository medicRepository;

    private ValidateMedicTelephone validateTelephone;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        validateTelephone = new ValidateMedicTelephone(medicRepository);
    }

    @Test
    @DisplayName("Should pass when telephone does not exist")
    void validateTelephoneScenario01() {
        MedicRegistDTO dto = new MedicRegistDTO("name", "SC123456", Speciality.CARDIOLOGIA,
                "(12) 02928-2029", "email@email.com");

        when(medicRepository.existsByTelephone(dto.telephone())).thenReturn(false);

        assertDoesNotThrow(() -> validateTelephone.validate(dto));
    }

    @Test
    @DisplayName("Should not pass when telephone exists")
    void validateTelephoneScenario02() {
        MedicRegistDTO dto = new MedicRegistDTO("name", "SC123456", Speciality.CARDIOLOGIA,
                "(12) 02928-2029", "email@email.com");

        when(medicRepository.existsByTelephone(dto.telephone())).thenReturn(true);

        assertThrows(ValidateException.class, () -> validateTelephone.validate(dto));
    }
}
