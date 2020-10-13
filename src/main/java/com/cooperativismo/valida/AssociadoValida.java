package com.cooperativismo.valida;

import com.cooperativismo.enums.HabilitacaoParaVoto;
import com.cooperativismo.externo.HabiitacaoVoto;
import com.cooperativismo.model.Associado;
import com.cooperativismo.service.AssociadoService;
import com.cooperativismo.service.PautaService;
import com.cooperativismo.service.VotoAssembleiaService;
import org.json.JSONException;

import java.io.IOException;

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
    public boolean valida() throws IOException, JSONException {
        return associadoHabilitadoParaVoto() && associadoDisponivelParaPauta();
    }

    private boolean associadoHabilitadoParaVoto() throws IOException, JSONException {
        Associado associado = associadoService.find(idAssociado);
        return new HabiitacaoVoto().isDisponivel(associado.getCpf())
                == HabilitacaoParaVoto.HABILITADO;
    }

    private boolean associadoDisponivelParaPauta(){
        return !votoAssembleiaService.existeVotoDoAssociadoNaPauta(idAssociado, idPauta);
    }
}
