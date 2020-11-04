package com.cooperativismo.domain.service;

import com.cooperativismo.api.model.ResultadoVotacaoDTO;
import com.cooperativismo.domain.enums.StatusSessao;
import com.cooperativismo.domain.model.Associado;
import com.cooperativismo.domain.model.Pauta;
import com.cooperativismo.domain.repository.AssociadoRepository;
import com.cooperativismo.domain.repository.PautaRepository;
import com.cooperativismo.domain.repository.VotoAssembleiaRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

@RunWith(SpringRunner.class)
public class VotoAssembleiaTests {

    @TestConfiguration
    static class VotoAssembleiaServiceConfiguration{

        @Bean
        public VotoAssembleiaService votoAssembleiaService(){
            return new VotoAssembleiaService();
        }
    }

    @Autowired
    VotoAssembleiaService votoAssembleiaService;

    @MockBean
    VotoAssembleiaRepository votoAssembleiaRepository;

    @MockBean
    PautaRepository pautaRepository;

    @MockBean
    AssociadoRepository associadoRepository;

    @Test
    public void votoAssembleiaTestServiceTotalVotos(){
        String idPauta = "111";
        Optional<ResultadoVotacaoDTO> resultadoVotacaoDTO = votoAssembleiaService.totalVotos(idPauta);
        int totalVotos = resultadoVotacaoDTO.get().getTotal();
        Assertions.assertEquals(1, totalVotos);
    }

    @Before
    public void setup(){
        Pauta pauta = new Pauta("teste").setId("111").setStatusSessao(StatusSessao.ABERTA);
        Associado associado = new Associado().setCpf("02692471067");
//        VotoAssembleia votoAssembleia = new VotoAssembleia().setPauta(pauta)
//                                                            .setAssociado(associado)
//                                                            .setVoto(Voto.SIM);
        ResultadoVotacaoDTO resultadoVotacaoDTO = new ResultadoVotacaoDTO().setVotosSim(1);
        Mockito.when(votoAssembleiaRepository.totalvotos(pauta.getId()))
                .thenReturn(java.util.Optional.of(resultadoVotacaoDTO));

    }

//    @Mock
//    private VotoAssembleiaRepository votoAssembleiaRepository;
//    @InjectMocks
//    private VotoAssembleiaService votoAssembleiaService;
//
//    private static final String id = "id";
//
//    private static VotoAssembleia votoAssembleiaMock;
//
//    @Test
//    public void saveSuccessTest() {
//        when(votoAssembleiaRepository.save(eq(votoAssembleiaMock))).thenReturn(votoAssembleiaMock);
//        VotoAssembleia votoAssembleiaRetornado = votoAssembleiaService.save(votoAssembleiaMock);
//        assertEquals(votoAssembleiaMock, votoAssembleiaRetornado);
//        verify(votoAssembleiaRepository).save(eq(votoAssembleiaMock));
//    }
}
