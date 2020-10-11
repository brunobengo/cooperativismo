package com.cooperativismo.dto;

public class IniciaPautaDTO {
    private String idPauta;
    private int duracao;

    public String getIdPauta() {
        return idPauta;
    }

    public IniciaPautaDTO setIdPauta(String idPauta) {
        this.idPauta = idPauta;
        return this;
    }

    public int getDuracao() {
        return duracao;
    }

    public IniciaPautaDTO setDuracao(int duracao) {
        this.duracao = duracao;
        return this;
    }
}
