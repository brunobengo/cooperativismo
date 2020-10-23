package com.cooperativismo.domain.service;

import com.cooperativismo.domain.model.Pauta;
import com.cooperativismo.domain.repository.PautaRepository;
import com.cooperativismo.domain.repository.VotoAssembleiaRepository;
import org.springframework.stereotype.Service;

@Service
public class PautaService {

    private final PautaRepository pautaRepository;
    private final VotoAssembleiaRepository votoAssembleiaRepository;

    public PautaService(PautaRepository pautaRepository, VotoAssembleiaRepository votoAssembleiaRepository) {
        this.pautaRepository = pautaRepository;
        this.votoAssembleiaRepository = votoAssembleiaRepository;
    }

    public Pauta findById(String id) {
        return pautaRepository.findById(id).get();
    }

}
