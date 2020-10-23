package com.cooperativismo.domain.service;

import com.cooperativismo.domain.model.VotoAssembleia;
import com.cooperativismo.domain.repository.VotoAssembleiaRepository;
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

    private static final String id = "id";

    private static VotoAssembleia votoAssembleiaMock;

    @Test
    public void saveSuccessTest() {
        when(votoAssembleiaRepository.save(eq(votoAssembleiaMock))).thenReturn(votoAssembleiaMock);
        VotoAssembleia votoAssembleiaRetornado = votoAssembleiaService.save(votoAssembleiaMock);
        assertEquals(votoAssembleiaMock, votoAssembleiaRetornado);
        verify(votoAssembleiaRepository).save(eq(votoAssembleiaMock));
    }
}
