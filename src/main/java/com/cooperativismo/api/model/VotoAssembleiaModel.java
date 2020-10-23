package com.cooperativismo.api.model;

import com.cooperativismo.domain.enums.Voto;

public class VotoAssembleiaModel {

    private String idPauta;
    private String idAssociado;
    private boolean confirma;

    public VotoAssembleiaModel(){

    }

    public String getIdPauta() {
        return idPauta;
    }

    public VotoAssembleiaModel setIdPauta(String idPauta) {
        this.idPauta = idPauta;
        return this;
    }

    public String getIdAssociado() {
        return idAssociado;
    }

    public VotoAssembleiaModel setIdAssociado(String idAssociado) {
        this.idAssociado = idAssociado;
        return this;
    }

    public VotoAssembleiaModel setVoto(Voto voto) {
        this.confirma = voto == Voto.SIM;
        return this;
    }

    public boolean isConfirma() {
        return confirma;
    }

}
