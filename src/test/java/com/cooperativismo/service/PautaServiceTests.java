package com.cooperativismo.service;

import com.cooperativismo.model.Pauta;
import com.cooperativismo.repository.PautaRepository;
import org.junit.jupiter.api.BeforeAll;
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
public class PautaServiceTests {

    @Mock
    private PautaRepository pautaRepository;
    @InjectMocks
    private PautaService pautaService;

    private static Pauta pautaMock;

    private static final String test_idPauta = "isPauta";

    @BeforeAll
    public static void setUp() {
        pautaMock = new Pauta()
                        .setDescricao("Nome da rua Tal");
    }

    @Test
    public void saveSuccessTest() {
        when(pautaRepository.save(eq(pautaMock))).thenReturn(pautaMock);
        Pauta pautaRetornada = pautaService.save(pautaMock);
        assertEquals(pautaMock, pautaRetornada);
        verify(pautaRepository).save(eq(pautaMock));
    }


//    -- by bruno ...

    @Test
    public void testSalva(){
        pautaService.save(pautaMock); //TODO qual assert utilizar?
    }

    @Test
    public void testAbreSecaoVotacao() throws InterruptedException {
        pautaService.save(pautaMock);
        pautaService.abreSessaoDeVotacao(pautaMock.getId(), 1);
        assertTrue(pautaService.isAberta(pautaMock.getId()));
   }

   @Test
    public void testFechamentoSecaoTempoDeterminado() throws InterruptedException {
        Pauta pauta = pautaService.save(new Pauta("Alterar relatório"));
        pautaService.abreSessaoDeVotacao(pauta.getId(), 1);
        assertTrue(pautaService.isAberta(pauta.getId()));
        Thread.sleep(60000);
        assertFalse(pautaService.isAberta(pauta.getId()));
    }

//    public void testFechamentoSecaoInatividade() throws InterruptedException {
//        Pauta pauta = new Pauta();
//        pauta.setDescricao("Altera procedimento");
//        pauta.abreSessao(60);
//        assertTrue(pauta.isAberta());
//        Associado associado1 = new Associado();
//        associado1.setCpf("1220233222");
//
//
//        Thread.sleep(60000);
//        assertFalse(pauta.isAberta());
//    }

    private void assertTrue(boolean resultadoObtido){
        assertEquals(true, resultadoObtido);
    }
    private void assertFalse(boolean resultadoObtido){
        assertEquals(false, resultadoObtido);
    }
}
