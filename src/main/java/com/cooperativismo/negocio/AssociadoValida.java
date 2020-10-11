package com.cooperativismo.negocio;

import com.cooperativismo.enums.HabilitacaoParaVoto;
import com.cooperativismo.externo.HabiitacaoVoto;
import com.cooperativismo.model.Associado;
import com.cooperativismo.service.AssociadoService;
import com.cooperativismo.service.PautaService;
import com.cooperativismo.service.VotoAssembleiaService;

public class AssociadoValida implements Valida {

    final private VotoAssembleiaService votoAssembleiaService;
    final private AssociadoService associadoService;
    final private PautaService pautaService;
    private String idAssociado, idPauta;

    public AssociadoValida(VotoAssembleiaService votoAssembleiaService,
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
        return associadoHabilitadoParaVoto() && associadoDisponivelParaPauta();
    }

    private boolean associadoHabilitadoParaVoto() {
        Associado associado = associadoService.find(idAssociado);
        if (associado.isHabilitacaoParaVoto()) {
            return new HabiitacaoVoto().isDisponivel(associado.getCpf())
                    == HabilitacaoParaVoto.HABILITADO;
        }
        return true;
    }

    private boolean associadoDisponivelParaPauta(){
        return !votoAssembleiaService.existeVotoDoAssociadoNaPauta(idAssociado, idPauta);
    }
}
