package com.cooperativismo.domain.repository;

import com.cooperativismo.api.model.ResultadoVotacaoDTO;
import org.json.JSONException;

import java.time.OffsetDateTime;
import java.util.Optional;

public interface VotoAssembleiaRepositoryQuery {

    boolean existeVotoDoAssociadoNaPauta(String idAssociado, String idPauta);

    OffsetDateTime horarioUltimaVotacao(String idPauta);

    Optional<ResultadoVotacaoDTO> totalvotos(String idPauta) throws JSONException;
}
