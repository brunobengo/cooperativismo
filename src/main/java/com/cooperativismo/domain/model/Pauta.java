package com.cooperativismo.domain.model;

import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import java.time.OffsetDateTime;

@Document
public class Pauta {

    @Id
    private String id;
    @NotBlank
    private String descricao;
    private boolean statusSessao;
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

    public String getDescricao() {
        return descricao;
    }

    public boolean isSessaoAberta() {
        return statusSessao;
    }

    public Pauta setStatusSessao(boolean statusSessao) {
        this.statusSessao = statusSessao;
        return this;
    }

    public OffsetDateTime getHoraAberturaAssembleia() {
        return horaAberturaAssembleia;
    }

    public Pauta setHoraAberturaAssembleia(OffsetDateTime horaAberturaAssembleia) {
        this.horaAberturaAssembleia = horaAberturaAssembleia;
        return this;
    }

    public int getMinutosDeDuracaoDaSessao() {
        return minutosDeDuracaoDaSessao;
    }

    public Pauta setMinutosDeDuracaoDaSessao(int minutosDeDuracaoDaSessao) {
        this.minutosDeDuracaoDaSessao = minutosDeDuracaoDaSessao;
        return this;
    }

    public Pauta setDescricao(String descricao) {
        this.descricao = descricao;
        return this;
    }

    public void iniciaAssembleia() {
        setHoraAberturaAssembleia(OffsetDateTime.now())
                .setStatusSessao(true);
    }
}