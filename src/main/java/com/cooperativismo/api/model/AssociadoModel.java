package com.cooperativismo.api.model;

public class AssociadoModel {

    private String id;
    private String cpf;
    private boolean habilitacaoParaVoto;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public boolean isHabilitacaoParaVoto() {
        return habilitacaoParaVoto;
    }

    public void setHabilitacaoParaVoto(boolean habilitacaoParaVoto) {
        this.habilitacaoParaVoto = habilitacaoParaVoto;
    }
}
