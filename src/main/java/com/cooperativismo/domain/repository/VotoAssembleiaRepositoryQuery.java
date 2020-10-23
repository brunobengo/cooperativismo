package com.cooperativismo.domain.repository;

import com.cooperativismo.api.model.ResultadoVotacaoDTO;
import org.json.JSONException;

import java.time.OffsetDateTime;

public interface VotoAssembleiaRepositoryQuery {

    boolean existeVotoDoAssociadoNaPauta(String idAssociado, String idPauta);

    OffsetDateTime horarioUltimaVotacao(String idPauta);

    ResultadoVotacaoDTO totalvotos(String idPauta) throws JSONException;
}
