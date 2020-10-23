package com.cooperativismo.domain.service;

import com.cooperativismo.domain.model.Pauta;
import com.cooperativismo.domain.repository.PautaRepository;
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

    private static final String id = "id";

    @BeforeAll
    public static void setUp() {
        pautaMock = new Pauta()
                        .setDescricao("Nome da rua Tal");
    }

    @Test
    public void saveSuccessTest() {
        when(pautaRepository.save(eq(pautaMock))).thenReturn(pautaMock);
        Pauta pautaRetornada = pautaRepository.save(pautaMock);
        assertEquals(pautaMock, pautaRetornada);
        verify(pautaRepository).save(eq(pautaMock));
    }
}
