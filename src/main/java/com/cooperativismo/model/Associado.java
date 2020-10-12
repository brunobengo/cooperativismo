package com.cooperativismo.model;

import lombok.*;

import javax.persistence.Id;
import java.io.Serializable;

@EqualsAndHashCode
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Associado implements Serializable {

    @Id
    private String id;
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

    public void setId(String id) {
        this.id = id;
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

    public void setHabilitacaoParaVoto(boolean habilitacaoParaVoto) {
        this.habilitacaoParaVoto = habilitacaoParaVoto;
    }
}