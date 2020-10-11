package com.cooperativismo.valida;

import com.cooperativismo.dto.VotoDTO;
import com.cooperativismo.service.AssociadoService;
import com.cooperativismo.service.PautaService;
import com.cooperativismo.service.VotoAssembleiaService;

public class VotoAssembleiaValida implements Valida{

    final private VotoAssembleiaService votoAssembleiaService;
    final private AssociadoService associadoService;
    final private PautaService pautaService;
    private String idAssociado, idPauta;

    public VotoAssembleiaValida(VotoAssembleiaService votoAssembleiaService,
                                AssociadoService associadoService, PautaService pautaService,
                                VotoDTO votoDTO) {
        this.votoAssembleiaService = votoAssembleiaService;
        this.associadoService = associadoService;
        this.pautaService = pautaService;
        this.idPauta = votoDTO.getIdPauta();
        this.idAssociado = votoDTO.getIdAssociado();
    }

    @Override
    public boolean valida(){
        return new AssociadoValida(votoAssembleiaService, associadoService, pautaService, idPauta, idAssociado)
                .valida()
                && new PautaValida(votoAssembleiaService, associadoService, pautaService, idPauta, idAssociado)
                .valida();
    }
}
