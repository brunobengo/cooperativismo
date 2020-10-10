package com.example.cooperativismo.document;

import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;


@Document
public class Pauta {

    @Id
    private String id;

    @NotBlank
    private String descricao;

    private boolean statusSessao;

    private LocalDateTime horaAberturaAssembleia;

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
        if(statusSessao)
            this.horaAberturaAssembleia = LocalDateTime.now();
        this.statusSessao = statusSessao;
    }

    public LocalDateTime getHoraAberturaAssembleia() {
        return horaAberturaAssembleia;
    }

    public void setHoraAberturaAssembleia(LocalDateTime horaAberturaAssembleia) {
        this.horaAberturaAssembleia = horaAberturaAssembleia;
    }
}
