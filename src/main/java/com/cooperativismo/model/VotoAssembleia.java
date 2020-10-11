package com.cooperativismo.model;

import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Document
public class VotoAssembleia {

    @Id
    private String id;

    @NotBlank
    private String idPauta;

    @NotBlank
    private String idAssociado;

    private String voto;

    private LocalDateTime horarioVoto;

    public void setId(String id) {
        this.id = id;
    }

    public String getIdAssociado() {
        return idAssociado;
    }

    public void setHorarioVoto(LocalDateTime horarioVoto) {
        this.horarioVoto = horarioVoto;
    }

    public String getId() {
        return id;
    }

    public String getIdPauta() {
        return idPauta;
    }

    public void setIdPauta(String idPauta) {
        this.idPauta = idPauta;
    }

    public String getCpfAssociado() {
        return getIdAssociado();
    }

    public void setIdAssociado(String idAssociado) {
        this.idAssociado = idAssociado;
    }

    public String getVoto() {
        return voto;
    }

    public void setVoto(String voto) {
        this.voto = voto;
    }

    public LocalDateTime getHorarioVoto() {
        return horarioVoto;
    }

}
