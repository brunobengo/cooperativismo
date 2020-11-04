package com.cooperativismo.domain.model;

import com.cooperativismo.domain.enums.StatusSessao;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import java.time.OffsetDateTime;

@Document
public class Pauta {

    @Id
    private String id;
    @NotBlank
    private String descricao;
    @Enumerated(EnumType.STRING)
    private StatusSessao statusSessao;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private OffsetDateTime horaAberturaAssembleia;
    private int minutosDeDuracaoDaSessao;

    public Pauta() {
    }

    public Pauta(String descricao) {
        this.descricao = descricao;
    }

    public String getId() {
        return id;
    }

    public Pauta setId(String id) {
        this.id = id;
        return this;
    }

    public String getDescricao() {
        return descricao;
    }

    public Pauta setDescricao(String descricao) {
        this.descricao = descricao;
        return this;
    }

    public StatusSessao getStatusSessao() {
        return statusSessao;
    }

    public Pauta setStatusSessao(StatusSessao statusSessao) {
        this.statusSessao = statusSessao;
        return this;
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