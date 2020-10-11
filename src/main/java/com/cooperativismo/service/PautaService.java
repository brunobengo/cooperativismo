package com.cooperativismo.service;

import com.cooperativismo.model.Pauta;
import com.cooperativismo.repository.PautaRepository;
import com.cooperativismo.repository.VotoAssembleiaRepositoryQueryImpl;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PautaService {

    private final PautaRepository pautaRepository;

    public PautaService(PautaRepository pautaRepository) {
        this.pautaRepository = pautaRepository;
    }

    public Pauta save(Pauta pauta){ return pautaRepository.save(pauta); }

    public Pauta find(String id){
        return pautaRepository.findById(id).get();
    }

    public Pauta abreSessaoDeVotacao(String idPauta, int minutosDeDuracaoDaSessao) {
        Pauta pauta = find(idPauta);
        pauta.setMinutosDeDuracaoDaSessao(minutosDeDuracaoDaSessao);
        pauta.setHoraAberturaAssembleia(LocalDateTime.now());
        pauta.setStatusSessao(true);
        save(pauta);
        return pauta;
    }

    private boolean isInativa(Pauta pauta){
        VotoAssembleiaRepositoryQueryImpl votoAssembleiaRepositoryQueryImpl = new VotoAssembleiaRepositoryQueryImpl();
        LocalDateTime horarioUltimaVotacao = votoAssembleiaRepositoryQueryImpl.horarioUltimaVotacao(pauta.getId());
        return LocalDateTime.now().minusMinutes(1).isAfter(horarioUltimaVotacao);
    }
    private boolean isJaFechou(Pauta pauta){
        LocalDateTime momentoDeFechamentoDaSecao =
                pauta.getHoraAberturaAssembleia().plusMinutes(pauta.getMinutosDeDuracaoDaSessao());
        return LocalDateTime.now().isAfter(momentoDeFechamentoDaSecao);
    }

    public boolean isAberta(String idPauta) {
        Pauta pauta = find(idPauta);
        if(pauta.isSessaoAberta()){
            if(isJaFechou(pauta) || isInativa(pauta)) {
                pauta.setStatusSessao(false);
                save(pauta);
            }
        }
        return pauta.isSessaoAberta();
    }
}
