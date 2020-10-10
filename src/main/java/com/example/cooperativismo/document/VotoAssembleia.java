package com.example.cooperativismo.document;

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
    private String cpfAssociado;

    private String voto;

    private LocalDateTime horarioVoto;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdPauta() {
        return idPauta;
    }

    public void setIdPauta(String idPauta) {
        this.idPauta = idPauta;
    }

    public String getCpfAssociado() {
        return cpfAssociado;
    }

    public void setCpfAssociado(String cpfAssociado) {
        this.cpfAssociado = cpfAssociado;
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

    public void setHorarioVoto(LocalDateTime horarioVoto) {
        this.horarioVoto = horarioVoto;
    }
}
