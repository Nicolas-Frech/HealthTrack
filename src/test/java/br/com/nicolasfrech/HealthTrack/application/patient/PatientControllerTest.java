package br.com.nicolasfrech.HealthTrack.application.patient;


import br.com.nicolasfrech.HealthTrack.application.medic.dto.MedicRegistDTO;
import br.com.nicolasfrech.HealthTrack.application.medic.dto.MedicReturnDTO;
import br.com.nicolasfrech.HealthTrack.application.patient.dto.PatientRegistDTO;
import br.com.nicolasfrech.HealthTrack.application.patient.dto.PatientReturnDTO;
import br.com.nicolasfrech.HealthTrack.application.patient.gateway.PatientRepository;
import br.com.nicolasfrech.HealthTrack.domain.medic.Medic;
import br.com.nicolasfrech.HealthTrack.domain.medic.Speciality;
import br.com.nicolasfrech.HealthTrack.domain.patient.Patient;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
public class PatientControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private JacksonTester<PatientRegistDTO> patientRegistDTOJson;

    @Autowired
    private JacksonTester<PatientReturnDTO> patientReturnDTOJson;

    @MockitoBean
    private PatientRepository patientRepository;

    @Test
    @DisplayName("Should return 400 code when invalid data")
    public void registScenario01() throws Exception {
        var response = mvc.perform(post("/patient")).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("Should return 201 code when valid data")
    public void registScenario02() throws Exception {
        PatientRegistDTO dto = new PatientRegistDTO("name", "063.837.709-93", 12,
                "email@email.com",
                "(48) 99999-2298");

        when(patientRepository.save(any())).thenReturn(new Patient(dto.name(), dto.cpf(), dto.age(),
                dto.email(), dto.telephone()));

        var response = mvc.perform(post("/patient")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(patientRegistDTOJson.write(dto).getJson()))
                .andReturn().getResponse();

        PatientReturnDTO patientReturnDTO = new PatientReturnDTO(null, dto.name(), dto.cpf(),
                dto.telephone(), dto.age(), dto.email(), true);

        var expectedJson = patientReturnDTOJson.write(patientReturnDTO).getJson();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
        assertThat(response.getContentAsString()).isEqualToIgnoringWhitespace(expectedJson);
    }
}
