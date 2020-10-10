package com.cooperativismo.model;

import lombok.*;

import javax.persistence.Id;
import java.io.Serializable;
import java.time.LocalDateTime;

@EqualsAndHashCode
@AllArgsConstructor
//@NoArgsConstructor
@Getter
@Setter
@Builder
public class Pauta implements Serializable {

    @Id
    private String id;
    private String descricao;
    private boolean statusSessao;
    private LocalDateTime horaAberturaAssembleia;
    private int minutosDeDuracaoDaSessao;

    public Pauta(){}
    public Pauta(String descricao){
        this.descricao = descricao;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public boolean isStatusSessao() {
        return statusSessao;
    }

    public void setStatusSessao(boolean statusSessao) {
        this.statusSessao = statusSessao;
    }

    public LocalDateTime getHoraAberturaAssembleia() {
        return horaAberturaAssembleia;
    }

    public void setHoraAberturaAssembleia(LocalDateTime horaAberturaAssembleia) {
        this.horaAberturaAssembleia = horaAberturaAssembleia;
    }

    public int getMinutosDeDuracaoDaSessao() {
        return minutosDeDuracaoDaSessao;
    }

    public void setMinutosDeDuracaoDaSessao(int minutosDeDuracaoDaSessao) {
        this.minutosDeDuracaoDaSessao = minutosDeDuracaoDaSessao;
    }

    public enum StatusSecao  {
        ABERTA, FECHADA;
    }

    public Pauta setDescricao(String descricao){
        this.descricao = descricao;
        return this;
    }

    public void abreSessao(int minutosDeDuracaoDaSessao) {
        this.setMinutosDeDuracaoDaSessao(minutosDeDuracaoDaSessao);
        this.setHoraAberturaAssembleia(LocalDateTime.now());
        this.setStatusSessao(true);
    }

    public boolean isAberta() {
        LocalDateTime momentoDeFechamentoDaSecao = getHoraAberturaAssembleia().plusMinutes(getMinutosDeDuracaoDaSessao());
        System.out.println(momentoDeFechamentoDaSecao.toString());
        this.setStatusSessao(LocalDateTime.now().isEqual(momentoDeFechamentoDaSecao)
                || LocalDateTime.now().isBefore(momentoDeFechamentoDaSecao));
        return this.isStatusSessao();
    }
}

