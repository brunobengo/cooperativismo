package com.cooperativismo.dto;

public class ResultadoVotacaoDTO {

    private String idPauta;
    private int VotosSim;
    private int VotosNao;

    public ResultadoVotacaoDTO(){
    }

    public int getVotosSim() {
        return VotosSim;
    }

    public ResultadoVotacaoDTO setVotosSim(int votosSim) {
        VotosSim = votosSim;
        return this;
    }

    public int getVotosNao() {
        return VotosNao;
    }

    public ResultadoVotacaoDTO setVotosNao(int votosNao) {
        VotosNao = votosNao;
        return this;
    }

    public int getTotal() {
        return getVotosSim() + getVotosNao();
    }

    public String getIdPauta() {
        return idPauta;
    }

    public ResultadoVotacaoDTO setIdPauta(String idPauta) {
        this.idPauta = idPauta;
        return this;
    }
}

