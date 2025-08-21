package br.com.nicolasfrech.HealthTrack.application.medic;


import br.com.nicolasfrech.HealthTrack.application.medic.dto.MedicRegistDTO;
import br.com.nicolasfrech.HealthTrack.domain.medic.Speciality;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class MedicServiceRegistTest {

    @Autowired
    private MedicService medicService;

    @Test
    @DisplayName("Should register valid medic")
    public void medicRegistScenario01() {
        MedicRegistDTO dto = new MedicRegistDTO("name", "SP123456", Speciality.CARDIOLOGIA,
                "(47) 99999-0000", "email@email.com");

        assertDoesNotThrow(() -> medicService.registMedic(dto));
    }

    @Test
    @DisplayName("Should not register medic with invalid CRM")
    public void medicRegistScenario02() {
        MedicRegistDTO dto = new MedicRegistDTO("name", "SP12", Speciality.CARDIOLOGIA,
                "(47) 99999-0000", "email@email.com");

        assertThrows(IllegalArgumentException.class, () -> medicService.registMedic(dto));
    }

    @Test
    @DisplayName("Should not register medic with invalid telephone")
    public void medicRegistScenario03() {
        MedicRegistDTO dto = new MedicRegistDTO("name", "SP123456", Speciality.CARDIOLOGIA,
                "(47) 9999", "email@email.com");

        assertThrows(IllegalArgumentException.class, () -> medicService.registMedic(dto));
    }

    @Test
    @DisplayName("Should not register medic with invalid email")
    public void medicRegistScenario04() {
        MedicRegistDTO dto = new MedicRegistDTO("name", "SP123456", Speciality.CARDIOLOGIA,
                "(47) 99999-8989", "email.com");

        assertThrows(IllegalArgumentException.class, () -> medicService.registMedic(dto));
    }
}
