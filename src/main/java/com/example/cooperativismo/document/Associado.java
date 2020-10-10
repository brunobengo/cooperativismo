package com.example.cooperativismo.document;

import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Document
public class Associado {

    @Id
    private String id;

    @NotBlank
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