package com.cooperativismo.service;

import com.cooperativismo.dto.IniciaPautaDTO;
import com.cooperativismo.model.Pauta;
import com.cooperativismo.repository.PautaRepository;
import com.cooperativismo.repository.VotoAssembleiaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PautaService {

    private final PautaRepository pautaRepository;
    private final VotoAssembleiaRepository votoAssembleiaRepository;

    public PautaService(PautaRepository pautaRepository, VotoAssembleiaRepository votoAssembleiaRepository) {
        this.pautaRepository = pautaRepository;
        this.votoAssembleiaRepository = votoAssembleiaRepository;
    }

    public Pauta save(Pauta pauta) {
        return pautaRepository.save(pauta);
    }

    public Pauta findById(String id) {
        return pautaRepository.findById(id).get();
    }

    public Pauta iniciaassembleia(IniciaPautaDTO iniciaPautaDTO) {
        Pauta pauta = findById(iniciaPautaDTO.getIdPauta());
        pauta.setMinutosDeDuracaoDaSessao(iniciaPautaDTO.getDuracao())
                .setHoraAberturaAssembleia(LocalDateTime.now())
                .setStatusSessao(true);
        save(pauta);
        return pauta;
    }
}
