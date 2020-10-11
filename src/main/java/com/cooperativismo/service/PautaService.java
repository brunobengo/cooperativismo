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

    public Pauta iniciaPauta(IniciaPautaDTO iniciaPautaDTO) {
        Pauta pauta = findById(iniciaPautaDTO.getIdPauta());
        pauta.setMinutosDeDuracaoDaSessao(iniciaPautaDTO.getDuracao());
        pauta.setHoraAberturaAssembleia(LocalDateTime.now());
        pauta.setStatusSessao(true);
        save(pauta);
        return pauta;
    }

    private boolean isInativa(Pauta pauta) {
        LocalDateTime horarioUltimaVotacao = votoAssembleiaRepository.horarioUltimaVotacao(pauta.getId());
        return LocalDateTime.now().minusMinutes(1).isAfter(horarioUltimaVotacao);
    }

    private boolean isJaFechou(Pauta pauta) {
        LocalDateTime momentoDeFechamentoDaSecao =
                pauta.getHoraAberturaAssembleia().plusMinutes(pauta.getMinutosDeDuracaoDaSessao());
        return LocalDateTime.now().isAfter(momentoDeFechamentoDaSecao);
    }

    public boolean isAberta(String idPauta) {
        Pauta pauta = findById(idPauta);
        if (pauta.isSessaoAberta()) {
            if (isJaFechou(pauta) || isInativa(pauta)) {
                pauta.setStatusSessao(false);
                save(pauta);
            }
        }
        return pauta.isSessaoAberta();
    }
}
