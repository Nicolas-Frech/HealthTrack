package br.com.nicolasfrech.HealthTrack.application.consultation;

import br.com.nicolasfrech.HealthTrack.application.consultation.dto.BookConsultationDTO;
import br.com.nicolasfrech.HealthTrack.application.consultation.dto.ConsultationReturnDTO;
import br.com.nicolasfrech.HealthTrack.application.consultation.gateway.ConsultationRepository;
import br.com.nicolasfrech.HealthTrack.application.medic.dto.MedicReturnDTO;
import br.com.nicolasfrech.HealthTrack.application.medic.gateway.MedicRepository;
import br.com.nicolasfrech.HealthTrack.application.patient.dto.PatientReturnDTO;
import br.com.nicolasfrech.HealthTrack.application.patient.gateway.PatientRepository;
import br.com.nicolasfrech.HealthTrack.domain.consultation.Consultation;
import br.com.nicolasfrech.HealthTrack.domain.consultation.ConsultationStatus;
import br.com.nicolasfrech.HealthTrack.domain.medic.Medic;
import br.com.nicolasfrech.HealthTrack.domain.medic.Speciality;
import br.com.nicolasfrech.HealthTrack.domain.patient.Patient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.time.Month;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
public class ConsultationControllerTest {

    private Medic medic;
    private Patient patient;

    @BeforeEach
    void setUp() {
        medic = new Medic(
                1L,
                "Medic",
                "SC123456",
                Speciality.CARDIOLOGIA,
                "(47) 99999-4949",
                "email@email.com",
                true
        );

        patient = new Patient(
                1L,
                "(12) 34567-1234",
                "Patient",
                "123.456.789-00",
                12 ,
                "email@email.com",
                true
        );
    }

    @Autowired
    private MockMvc mvc;

    @Autowired
    private JacksonTester<BookConsultationDTO> bookConsultationDTOJson;

    @Autowired
    private JacksonTester<ConsultationReturnDTO> consultationReturnDTOJson;

    @MockitoBean
    private ConsultationRepository consultationRepository;

    @MockitoBean
    private MedicRepository medicRepository;

    @MockitoBean
    private PatientRepository patientRepository;

    @Test
    @DisplayName("Should return 400 code when invalid data")
    @WithMockUser(roles = "ADMIN")
    public void registScenario01() throws Exception {
        var response = mvc.perform(post("/consultation")).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("Should return 201 code when valid data")
    @WithMockUser(roles = "ADMIN")
    public void registScenario02() throws Exception {
        var date = LocalDateTime.of(2031, Month.AUGUST, 25, 10, 0);
        BookConsultationDTO dto = new BookConsultationDTO("SC123456", "123.456.789-00",
                date);


        when(medicRepository.existsByCrmAndActiveTrue("SC123456")).thenReturn(true);
        when(patientRepository.existsByCpfAndActiveTrue("123.456.789-00")).thenReturn(true);

        when(medicRepository.findByCrmAndActiveTrue("SC123456")).thenReturn(medic);
        when(patientRepository.findByCpfAndActiveTrue("123.456.789-00")).thenReturn(patient);

        when(consultationRepository.existsByMedicIdAndDateAndStatus(medic.getId(), date, ConsultationStatus.SCHEDULED)).thenReturn(false);
        when(consultationRepository.existsByPatientIdAndDateAndStatus(patient.getId(), date, ConsultationStatus.SCHEDULED)).thenReturn(false);
        when(consultationRepository.save(any())).thenReturn(new Consultation(medic, patient, date));

        var response = mvc.perform(post("/consultation")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(bookConsultationDTOJson.write(dto).getJson()))
                .andReturn().getResponse();

        ConsultationReturnDTO consultationReturnDTO = new ConsultationReturnDTO(
                null,
                new MedicReturnDTO(medic),
                new PatientReturnDTO(patient),
                date,
                ConsultationStatus.SCHEDULED,
                null,
                null
        );

        var expectedJson = consultationReturnDTOJson.write(consultationReturnDTO).getJson();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
        assertThat(response.getContentAsString()).isEqualToIgnoringWhitespace(expectedJson);
    }
}
