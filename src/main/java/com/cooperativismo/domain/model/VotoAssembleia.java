package com.cooperativismo.domain.model;

import com.cooperativismo.domain.enums.Voto;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.OffsetDateTime;

@Document
public class VotoAssembleia {

    @Id
    private String id;

    @ManyToOne
    private Pauta pauta;

    @ManyToOne
    private Associado associado;

//    @NotBlank
    @Enumerated(EnumType.STRING)
    private Voto voto;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private OffsetDateTime horarioVoto;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Pauta getPauta() {
        return pauta;
    }

    public VotoAssembleia setPauta(Pauta pauta) {
        this.pauta = pauta;
        return this;
    }

    public Associado getAssociado() {
        return associado;
    }

    public VotoAssembleia setAssociado(Associado associado) {
        this.associado = associado;
        return this;
    }

    public Voto getVoto() {
        return voto;
    }

    public VotoAssembleia setVoto(Voto voto) {
        this.voto = voto;
        return this;
    }

    public OffsetDateTime getHorarioVoto() {
        return horarioVoto;
    }

    public void setHorarioVoto(OffsetDateTime horarioVoto) {
        this.horarioVoto = horarioVoto;
    }
}
