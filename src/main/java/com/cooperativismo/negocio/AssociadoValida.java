package com.cooperativismo.negocio;

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
        if(associadoNaoHabilitadoParaVoto()){
            return false;
        }else if(associadoJaVotouNaPauta()){
            return false;
        }
        return true;
    }

    private boolean associadoNaoHabilitadoParaVoto(){
        Associado associado = associadoService.find(idAssociado);
        if(associado.isHabilitacaoParaVoto()){
            return new HabiitacaoVoto().isIndisponivel(associado.getCpf());
        }
        return true;
    }



    private boolean associadoJaVotouNaPauta(){
        votoAssembleiaService.existeVotoDoAssociadoNaPauta(idAssociado, idPauta);
        return true;
    }
}
