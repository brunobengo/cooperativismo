package com.cooperativismo.domain.model;

import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import java.time.OffsetDateTime;

@Document
public class VotoAssembleia {

    @Id
    private String id;

    @NotBlank
    private String idPauta;

    @NotBlank
    private String idAssociado;

    @NotBlank
    private String voto;

    private OffsetDateTime horarioVoto;

    public String getIdAssociado() {
        return idAssociado;
    }

    public VotoAssembleia setHorarioVoto(OffsetDateTime horarioVoto) {
        this.horarioVoto = horarioVoto;
        return this;
    }

    public String getId() {
        return id;
    }

    public String getIdPauta() {
        return idPauta;
    }

    public VotoAssembleia setIdPauta(String idPauta) {
        this.idPauta = idPauta;
        return this;
    }

    public String getCpfAssociado() {
        return getIdAssociado();
    }

    public VotoAssembleia setIdAssociado(String idAssociado) {
        this.idAssociado = idAssociado;
        return this;
    }

    public String getVoto() {
        return voto;
    }

    public VotoAssembleia setVoto(String voto) {
        this.voto = voto;
        return this;
    }

    public OffsetDateTime getHorarioVoto() {
        return horarioVoto;
    }

}
