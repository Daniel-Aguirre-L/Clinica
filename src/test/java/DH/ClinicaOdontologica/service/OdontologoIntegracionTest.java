package DH.ClinicaOdontologica.service;


import DH.ClinicaOdontologica.entity.Odontologo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class OdontologoIntegracionTest {

    @Autowired
    private OdontologoService odontologoService;

    @Autowired
    private MockMvc mockMvc;

    private void cargarDatos() {
        Odontologo odontologo = odontologoService.guardarOdontologo(new Odontologo("MP10", "Gina", "Arias"));
    }

    @Test
    public void registrarNuevoOdontologoTest() throws Exception {
        cargarDatos();


        Odontologo odontologo = new Odontologo("MP20", "Carlos", "Gomez");


        String odontologoJson = new ObjectMapper().writeValueAsString(odontologo);

        MvcResult respuesta = mockMvc.perform(MockMvcRequestBuilders.post("/odontologos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(odontologoJson))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        String responseContent = respuesta.getResponse().getContentAsString();
        assertFalse(responseContent.isEmpty());


        assertTrue(responseContent.contains("\"nombre\":\"Carlos\""));
        assertTrue(responseContent.contains("\"apellido\":\"Gomez\""));
        assertTrue(responseContent.contains("\"matricula\":\"MP20\""));
    }
}