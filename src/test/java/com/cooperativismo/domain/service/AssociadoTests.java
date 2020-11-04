package com.cooperativismo.domain.service;

import com.cooperativismo.domain.model.Associado;
import com.cooperativismo.domain.repository.AssociadoRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class AssociadoTests {

//    @Autowired
//    AssociadoService associadoService;
//
//    @Test
//    public void

//    @Mock
//    private AssociadoRepository associadoRepository;
//    @InjectMocks
//    private AssociadoService associadoService;
//
//    private static Associado associadoMock;
//
//    @BeforeAll
//    public static void setUp() {
//        associadoMock = new Associado()
//                .setCpf("Nome da rua Tal");
//    }
//
//    @Test
//    public void saveSuccessTest() {
//        when(associadoRepository.save(eq(associadoMock))).thenReturn(associadoMock);
//        Associado associadoRetornada = associadoRepository.save(associadoMock);
//        assertEquals(associadoMock, associadoRetornada);
//        verify(associadoRepository).save(eq(associadoMock));
//    }
//
//    private void assertTrue(boolean resultadoObtido){
//        assertEquals(true, resultadoObtido);
//    }
//    private void assertFalse(boolean resultadoObtido){
//        assertEquals(false, resultadoObtido);
//    }
}
