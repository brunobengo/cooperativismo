package com.cooperativismo.service;

import com.cooperativismo.model.Associado;
import com.cooperativismo.repository.AssociadoRepository;
import org.springframework.stereotype.Service;

@Service
public class AssociadoService {

    private final AssociadoRepository associadoRepository;

    public AssociadoService(AssociadoRepository associadoRepository){
        this.associadoRepository = associadoRepository;
    }
    public Associado save(Associado associado){
        return associadoRepository.save(associado);
    }
}
