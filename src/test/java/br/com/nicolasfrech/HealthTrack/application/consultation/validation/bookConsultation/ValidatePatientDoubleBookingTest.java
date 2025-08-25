package br.com.nicolasfrech.HealthTrack.application.consultation.validation.bookConsultation;

import br.com.nicolasfrech.HealthTrack.application.consultation.dto.BookConsultationDTO;
import br.com.nicolasfrech.HealthTrack.application.consultation.gateway.ConsultationRepository;
import br.com.nicolasfrech.HealthTrack.application.consultation.validation.hourValidation.ValidatePatientDoubleBooking;
import br.com.nicolasfrech.HealthTrack.application.patient.gateway.PatientRepository;
import br.com.nicolasfrech.HealthTrack.domain.consultation.ConsultationStatus;
import br.com.nicolasfrech.HealthTrack.domain.patient.Patient;
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

public class ValidatePatientDoubleBookingTest {

    @Mock
    private ConsultationRepository consultationRepository;

    @Mock
    private PatientRepository patientRepository;

    @InjectMocks
    private ValidatePatientDoubleBooking validatePatientDoubleBooking;

    private BookConsultationDTO dto;
    private Patient patient;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        patient = new Patient("name", "063.837.709-93", 28, "email@email.com",
                "(99) 99999-4444");

        dto = new BookConsultationDTO("SC123456", "063.837.709-93",
                LocalDateTime.now().plusDays(1).withHour(14).withMinute(30)
        );
    }

    @Test
    @DisplayName("Should not throw exception if patient has no other consultation")
    void patientDoubleBookingScenario01() {
        when(patientRepository.findByCpfAndActiveTrue(dto.patientCPF())).thenReturn(patient);
        when(consultationRepository.existsByPatientIdAndDateAndStatus(patient.getId(), dto.date(), ConsultationStatus.SCHEDULED))
                .thenReturn(false);

        assertDoesNotThrow(() -> validatePatientDoubleBooking.validate(dto));
    }

    @Test
    @DisplayName("Should throw exception if patient has consultation on the same hour")
    void patientDoubleBookingScenario02() {
        when(patientRepository.findByCpfAndActiveTrue(dto.patientCPF())).thenReturn(patient);
        when(consultationRepository.existsByPatientIdAndDateAndStatus(patient.getId(), dto.date(), ConsultationStatus.SCHEDULED))
                .thenReturn(true);

        assertThrows(ValidateException.class, () -> validatePatientDoubleBooking.validate(dto));
    }
}
