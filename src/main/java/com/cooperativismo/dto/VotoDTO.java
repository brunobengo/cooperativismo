package com.cooperativismo.dto;

import com.cooperativismo.enums.Voto;

public class VotoDTO {

    private String idPauta;
    private String idAssociado;
    private Voto voto;

    public VotoDTO(){

    }

    public String getIdPauta() {
        return idPauta;
    }

    public VotoDTO setIdPauta(String idPauta) {
        this.idPauta = idPauta;
        return this;
    }

    public String getIdAssociado() {
        return idAssociado;
    }

    public VotoDTO setIdAssociado(String idAssociado) {
        this.idAssociado = idAssociado;
        return this;
    }

    public Voto getVoto() {
        return voto;
    }

    public VotoDTO setVoto(Voto voto) {
        this.voto = voto;
        return this;
    }
}
