package com.cooperativismo.domain.model;

import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Document
public class Associado {

    @Id
    private String id;
    @NotBlank
    private String cpf;
    private boolean habilitacaoParaVoto;

    public Associado() {
    }

    public Associado(String cpf) {
        this.cpf = cpf;
    }

    public String getId() {
        return id;
    }

    public Associado setId(String id) {
        this.id = id;
        return this;
    }

    public String getCpf() {
        return cpf;
    }

    public Associado setCpf(String cpf) {
        this.cpf = cpf;
        return this;
    }

    public boolean isHabilitacaoParaVoto() {
        return habilitacaoParaVoto;
    }

    public Associado setHabilitacaoParaVoto(boolean habilitacaoParaVoto) {
        this.habilitacaoParaVoto = habilitacaoParaVoto;
        return this;
    }
}