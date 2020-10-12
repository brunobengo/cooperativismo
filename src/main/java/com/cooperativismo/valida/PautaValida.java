package com.cooperativismo.valida;

import com.cooperativismo.model.Pauta;
import com.cooperativismo.service.AssociadoService;
import com.cooperativismo.service.PautaService;
import com.cooperativismo.service.VotoAssembleiaService;

import java.time.LocalDateTime;

public class PautaValida implements Valida {

    final private VotoAssembleiaService votoAssembleiaService;
    final private AssociadoService associadoService;
    final private PautaService pautaService;
    private String idAssociado, idPauta;

    public PautaValida(VotoAssembleiaService votoAssembleiaService,
                           AssociadoService associadoService, PautaService pautaService,
                           String idPauta, String idAssociado) {
        this.votoAssembleiaService = votoAssembleiaService;
        this.associadoService = associadoService;
        this.pautaService = pautaService;
        this.idPauta = idPauta;
        this.idAssociado = idAssociado;
    }

    @Override
    public boolean valida() {
        return isAberta(idPauta);
    }

    private boolean isInativa(Pauta pauta) {
        LocalDateTime horarioUltimaVotacao = votoAssembleiaService.horarioUltimaVotacao(pauta.getId());
        return LocalDateTime.now().minusMinutes(1).isAfter(horarioUltimaVotacao);
    }

    private boolean isJaFechou(Pauta pauta) {
        LocalDateTime momentoDeFechamentoDaSecao =
                pauta.getHoraAberturaAssembleia().plusMinutes(pauta.getMinutosDeDuracaoDaSessao());
        return LocalDateTime.now().isAfter(momentoDeFechamentoDaSecao);
    }

    private boolean isAberta(String idPauta) {
        Pauta pauta = pautaService.findById(idPauta);
        if (pauta.isSessaoAberta()) {
            if (isJaFechou(pauta) || isInativa(pauta)) {
                pauta.setStatusSessao(false);
                pautaService.save(pauta);
            }
        }
        return pauta.isSessaoAberta();
    }
}
