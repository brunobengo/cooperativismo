package com.cooperativismo.domain.service;

import com.cooperativismo.domain.model.Associado;
import com.cooperativismo.domain.repository.AssociadoRepository;
import org.springframework.stereotype.Service;

@Service
public class AssociadoService {

    private final AssociadoRepository associadoRepository;

    public AssociadoService(AssociadoRepository associadoRepository) {
        this.associadoRepository = associadoRepository;
    }
}
