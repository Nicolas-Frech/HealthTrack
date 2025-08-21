package br.com.nicolasfrech.HealthTrack.application.medic;

import br.com.nicolasfrech.HealthTrack.application.medic.dto.MedicRegistDTO;
import br.com.nicolasfrech.HealthTrack.application.medic.dto.MedicReturnDTO;
import br.com.nicolasfrech.HealthTrack.application.medic.gateway.MedicRepository;
import br.com.nicolasfrech.HealthTrack.domain.medic.Medic;
import br.com.nicolasfrech.HealthTrack.domain.medic.Speciality;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
public class MedicControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private JacksonTester<MedicRegistDTO> medicRegistDTOJson;

    @Autowired
    private JacksonTester<MedicReturnDTO> medicReturnDTOJson;

    @Mock
    private MedicRepository medicRepository;

    @Test
    @DisplayName("Should return 400 code when invalid data")
    public void registScenario01() throws Exception {
        var response = mvc.perform(post("/medic")).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("Should return 200 code when valid data")
    public void registScenario02() throws Exception {
        MedicRegistDTO dto = new MedicRegistDTO("name", "SC234567",
                Speciality.CARDIOLOGIA, "(47) 99993-3937", "email@email.com");

        when(medicRepository.save(any())).thenReturn(new Medic(dto.name(), dto.crm(), dto.speciality(),
        dto.telephone(), dto.email()));

        var response = mvc.perform(post("/medic")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(medicRegistDTOJson.write(dto).getJson()))
                .andReturn().getResponse();

        MedicReturnDTO medicReturnDTO = new MedicReturnDTO(null, dto.name(), dto.crm(),
        dto.speciality(), dto.telephone(), dto.email(), true);

        var expectedJson = medicReturnDTOJson.write(medicReturnDTO).getJson();

        assertThat(response.getContentAsString()).isEqualTo(expectedJson);
    }
}
