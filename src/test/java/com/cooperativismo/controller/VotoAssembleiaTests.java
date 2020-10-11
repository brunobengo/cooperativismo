package com.cooperativismo.controller;

import com.cooperativismo.dto.ResultadoVotacaoDTO;
import com.cooperativismo.dto.VotoDTO;
import com.cooperativismo.enums.Voto;
import com.cooperativismo.model.Pauta;
import com.cooperativismo.service.VotoAssembleiaService;
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
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(VotoAssembleiaController.class)
public class VotoAssembleiaTests {

    @Autowired
    private MockMvc mockMvc;

    private static ObjectMapper mapper;
    @MockBean
    private VotoAssembleiaService votoAssembleiaService;

    private static final String id = "id";

    @BeforeAll
    public static void setUp() {
        mapper = new ObjectMapper();
    }

    @Test
    public void adicionaVotoTest() throws Exception {
        VotoDTO votoDTO = new VotoDTO()
                .setIdAssociado("2020203")
                .setIdPauta(id)
                .setVoto(Voto.SIM);
        doNothing().when(votoAssembleiaService).adicionaVoto(eq(votoDTO));
        mockMvc.perform(post("/voto/novovoto")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(votoDTO)))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    public void totalVotosTest() throws Exception {
        ResultadoVotacaoDTO resultadoVotacaoDTO = new ResultadoVotacaoDTO().setVotosNao(1).setVotosSim(2);
        when(votoAssembleiaService.totalVotos(id)).thenReturn(resultadoVotacaoDTO);

        mockMvc.perform(get((String.format("%s/%s/%s", "/voto", id, "resultado"))))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.votosSim").value(resultadoVotacaoDTO.getVotosSim()))
                .andExpect(jsonPath("$.votosNao").value(resultadoVotacaoDTO.getVotosNao()))
                .andExpect(jsonPath("$.total").value(resultadoVotacaoDTO.getTotal()));
    }


}
