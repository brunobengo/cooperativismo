package com.cooperativismo.dto;

import com.cooperativismo.enums.Voto;

public class VotoDTO {

    private String idPauta;
    private String idAssociado;
    private boolean confirma;

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

    public VotoDTO setVoto(Voto voto) {
        this.confirma = voto == Voto.SIM;
        return this;
    }

    public boolean isConfirma() {
        return confirma;
    }

}
