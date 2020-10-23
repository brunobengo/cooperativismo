package com.cooperativismo.api.model;

import java.time.OffsetDateTime;

public class PautaModel {

    private String id;
    private String descricao;
    private boolean statusSessao;
    private OffsetDateTime horaAberturaAssembleia;
    private int minutosDeDuracaoDaSessao;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public boolean isStatusSessao() {
        return statusSessao;
    }

    public void setStatusSessao(boolean statusSessao) {
        this.statusSessao = statusSessao;
    }

    public OffsetDateTime getHoraAberturaAssembleia() {
        return horaAberturaAssembleia;
    }

    public void setHoraAberturaAssembleia(OffsetDateTime horaAberturaAssembleia) {
        this.horaAberturaAssembleia = horaAberturaAssembleia;
    }

    public int getMinutosDeDuracaoDaSessao() {
        return minutosDeDuracaoDaSessao;
    }

    public void setMinutosDeDuracaoDaSessao(int minutosDeDuracaoDaSessao) {
        this.minutosDeDuracaoDaSessao = minutosDeDuracaoDaSessao;
    }
}
