package com.cooperativismo.service;

import com.cooperativismo.model.Pauta;
import com.cooperativismo.repository.PautaRepository;
import org.springframework.stereotype.Service;

@Service
public class PautaService {

    private final PautaRepository pautaRepository;

    public PautaService(PautaRepository pautaRepository) {
        this.pautaRepository = pautaRepository;
    }

    public Pauta save(Pauta pauta){ return pautaRepository.save(pauta); }

}
