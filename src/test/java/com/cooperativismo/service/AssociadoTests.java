package com.cooperativismo.service;

import com.cooperativismo.model.Associado;
import com.cooperativismo.repository.AssociadoRepository;
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
public class AssociadoTests {

    @Mock
    private AssociadoRepository associadoRepository;
    @InjectMocks
    private AssociadoService associadoService;

    private static Associado associadoMock;

    @BeforeAll
    public static void setUp() {
        associadoMock = new Associado()
                .setCpf("Nome da rua Tal");
    }

    @Test
    public void saveSuccessTest() {
        when(associadoRepository.save(eq(associadoMock))).thenReturn(associadoMock);
        Associado associadoRetornada = associadoService.save(associadoMock);
        assertEquals(associadoMock, associadoRetornada);
        verify(associadoRepository).save(eq(associadoMock));
    }


//  by Bruno
    @Test
    public void testSalva(){
        associadoMock = new Associado();
        associadoMock.setCpf("1220233222");
        associadoService.save(associadoMock); //TODO qual assert utilizar?
    }

    private void assertTrue(boolean resultadoObtido){
        assertEquals(true, resultadoObtido);
    }
    private void assertFalse(boolean resultadoObtido){
        assertEquals(false, resultadoObtido);
    }
}
