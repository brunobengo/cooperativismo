package com.cooperativismo.repository;

import com.cooperativismo.model.VotoAssembleia;

import java.time.LocalDateTime;
import java.util.List;

public interface VotoAssembleiaRepositoryQuery {

    boolean existeVotoDoAssociadoNaPauta(String idAssociado, String idPauta);

    LocalDateTime horarioUltimaVotacao(String idPauta);

    List<VotoAssembleia> totalvotos(String idPauta);
}
