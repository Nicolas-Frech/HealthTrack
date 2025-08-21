package br.com.nicolasfrech.HealthTrack.application.medic;



import br.com.nicolasfrech.HealthTrack.application.medic.gateway.MedicRepository;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class MedicServiceRegistTest {

    @Autowired
    private MedicService medicService;

    @Mock
    private MedicRepository medicRepository;
}
