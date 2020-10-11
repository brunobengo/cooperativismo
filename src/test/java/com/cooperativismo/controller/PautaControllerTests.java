package com.cooperativismo.controller;

import com.cooperativismo.dto.IniciaPautaDTO;
import com.cooperativismo.dto.ResultadoVotacaoDTO;
import com.cooperativismo.model.Pauta;
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
@WebMvcTest(PautaController.class)
public class PautaControllerTests {

    @Autowired
    private MockMvc mockMvc;

    private static ObjectMapper mapper;
    @MockBean
    private PautaService pautaService;

    private static final String id = "id";

    @BeforeAll
    public static void setUp() {
        mapper = new ObjectMapper();
    }

    @Test
    public void criaPautaTest() throws Exception {
        Pauta pauta = new Pauta("Pauta testee");
        when(pautaService.save(any(Pauta.class))).thenReturn(pauta);
        mockMvc.perform(post("/pauta/novapauta")
                .content(mapper.writeValueAsString(pauta))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful());
    }
}
