package com.cooperativismo.service;

import com.cooperativismo.enums.Voto;
import com.cooperativismo.model.Associado;
import com.cooperativismo.model.Pauta;
import com.cooperativismo.model.VotoAssembleia;
import com.cooperativismo.repository.VotoAssembleiaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class VotoAssembleiaTests {

    @Mock
    private VotoAssembleiaRepository votoAssembleiaRepository;
    @InjectMocks
    private VotoAssembleiaService votoAssembleiaService;
    @InjectMocks
    private PautaService pautaService;

    private static VotoAssembleia votoAssembleiaMock;

    @Test
    public void saveSuccessTest() {
        when(votoAssembleiaRepository.save(eq(votoAssembleiaMock))).thenReturn(votoAssembleiaMock);
        VotoAssembleia votoAssembleiaRetornado = votoAssembleiaService.save(votoAssembleiaMock);
        assertEquals(votoAssembleiaMock, votoAssembleiaRetornado);
        verify(votoAssembleiaRepository).save(eq(votoAssembleiaMock));
    }

//    by Bruno
    @Test
    public void testVotacao() throws Exception {
        Pauta pauta = pautaService
                .abreSessaoDeVotacao(new Pauta("Altera procedimento").getId(), 1);
        Associado associado = new Associado("1220233222");
        votoAssembleiaService.adicionaVoto(pauta.getId(), associado.getId(), Voto.SIM);
    }

    private void assertTrue(boolean resultadoObtido){
        assertEquals(true, resultadoObtido);
    }
    private void assertFalse(boolean resultadoObtido){
        assertEquals(false, resultadoObtido);
    }
}
