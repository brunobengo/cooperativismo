package com.cooperativismo.domain.service;

import com.cooperativismo.domain.enums.StatusSessao;
import com.cooperativismo.domain.exceptions.NegocioException;
import com.cooperativismo.domain.model.Pauta;
import com.cooperativismo.domain.repository.PautaRepository;
import com.cooperativismo.domain.repository.VotoAssembleiaRepository;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;

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

    public Pauta iniciaAssembleia(Pauta pautaInbox) {
        Pauta pauta = pautaRepository.findById(pautaInbox.getId())
                .orElseThrow(() -> new NegocioException("Pauta não localizada"));
        pauta.setStatusSessao(StatusSessao.ABERTA);
        pauta.setHoraAberturaAssembleia(OffsetDateTime.now());
        pauta.setMinutosDeDuracaoDaSessao(pautaInbox.getMinutosDeDuracaoDaSessao());
        return pautaRepository.save(pauta);
    }
}
