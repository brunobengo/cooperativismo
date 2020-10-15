package com.cooperativismo.controller;

import com.cooperativismo.model.Associado;
import com.cooperativismo.model.Pauta;
import com.cooperativismo.service.AssociadoService;
import com.cooperativismo.service.PautaService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(AssociadoController.class)
public class AssociadoControllerTests {

    @Autowired
    private MockMvc mockMvc;

    private static ObjectMapper mapper;
    @MockBean
    private AssociadoService associadoService;

    private static final String id = "id";

    @BeforeAll
    public static void setUp() {
        mapper = new ObjectMapper();
    }

    @Test
    public void novoAssociadoService() throws Exception {
        Associado associado = new Associado("12022203323");
        when(associadoService.save(any(Associado.class))).thenReturn(associado);
        mockMvc.perform(post("/associado/v1/novoassociado")
                .content(mapper.writeValueAsString(associado))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful());
    }
}