package br.com.nicolasfrech.HealthTrack.application.consultation.validation.bookConsultation;

import br.com.nicolasfrech.HealthTrack.application.consultation.dto.BookConsultationDTO;
import br.com.nicolasfrech.HealthTrack.application.consultation.gateway.ConsultationRepository;
import br.com.nicolasfrech.HealthTrack.application.consultation.validation.hourValidation.ValidateMedicDoubleBooking;
import br.com.nicolasfrech.HealthTrack.application.medic.gateway.MedicRepository;
import br.com.nicolasfrech.HealthTrack.domain.consultation.ConsultationStatus;
import br.com.nicolasfrech.HealthTrack.domain.medic.Medic;
import br.com.nicolasfrech.HealthTrack.domain.medic.Speciality;
import br.com.nicolasfrech.HealthTrack.infra.exception.ValidateException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

public class ValidateMedicDoubleBookingTest {

    @Mock
    private ConsultationRepository consultationRepository;

    @Mock
    private MedicRepository medicRepository;

    @InjectMocks
    private ValidateMedicDoubleBooking validateMedicDoubleBooking;

    private BookConsultationDTO dto;
    private Medic medic;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        medic = new Medic("name", "SC123456",
                Speciality.CARDIOLOGIA, "(47) 99999-3434", "email@email.com");

        dto = new BookConsultationDTO("SC123456", "123.456.789-00",
                LocalDateTime.now().plusDays(1).withHour(14).withMinute(30)
        );
    }

    @Test
    @DisplayName("Should not throw exception if medic has no other consultation")
    void medicDoubleBookingScenario01() {
        when(medicRepository.findByCrmAndActiveTrue(dto.medicCRM())).thenReturn(medic);
        when(consultationRepository.existsByMedicIdAndDateAndStatus(medic.getId(), dto.date(), ConsultationStatus.SCHEDULED))
                .thenReturn(false);

        assertDoesNotThrow(() -> validateMedicDoubleBooking.validate(dto));
    }

    @Test
    @DisplayName("Should throw exception if medic has consultation on the same hour")
    void medicDoubleBookingScenario02() {
        when(medicRepository.findByCrmAndActiveTrue(dto.medicCRM())).thenReturn(medic);
        when(consultationRepository.existsByMedicIdAndDateAndStatus(medic.getId(), dto.date(), ConsultationStatus.SCHEDULED))
                .thenReturn(true);

        assertThrows(ValidateException.class, () -> validateMedicDoubleBooking.validate(dto));
    }
}
