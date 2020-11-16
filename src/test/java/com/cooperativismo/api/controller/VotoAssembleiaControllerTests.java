package com.cooperativismo.api.controller;

import com.cooperativismo.domain.enums.StatusSessao;
import com.cooperativismo.domain.enums.Voto;
import com.cooperativismo.domain.model.Associado;
import com.cooperativismo.domain.model.Pauta;
import com.cooperativismo.domain.model.VotoAssembleia;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class VotoAssembleiaControllerTests {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void votoAssembleiaTestGetAll() throws Exception {
        mockMvc.perform(get("/assembleia"))
                .andExpect(status().isOk());
    }

    @Test
    public void votoAssembleiaTestSave() throws Exception {
        Pauta pauta = new Pauta("teste").setId("111").setStatusSessao(StatusSessao.ABERTA);
        Associado associado = new Associado().setCpf("02692471067");
        VotoAssembleia votoAssembleia = new VotoAssembleia().setPauta(pauta)
                .setAssociado(associado)
                .setVoto(Voto.SIM);
        mockMvc.perform(post("/assembleia")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(votoAssembleia)))
                .andExpect(status().isOk());
    }

    @Before
    public void setup() {

//        ResultadoVotacaoDTO resultadoVotacaoDTO = new ResultadoVotacaoDTO().setVotosSim(1);
////        Mockito.when(votoAssembleiaRepository.totalvotos(pauta.getId()))
//                .thenReturn(java.util.Optional.of(resultadoVotacaoDTO));

    }
}
