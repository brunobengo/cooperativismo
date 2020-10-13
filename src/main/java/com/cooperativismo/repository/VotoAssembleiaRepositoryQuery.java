package com.cooperativismo.repository;

import com.cooperativismo.dto.ResultadoVotacaoDTO;
import org.json.JSONException;

import java.time.LocalDateTime;

public interface VotoAssembleiaRepositoryQuery {

    boolean existeVotoDoAssociadoNaPauta(String idAssociado, String idPauta);

    LocalDateTime horarioUltimaVotacao(String idPauta);

    ResultadoVotacaoDTO totalvotos(String idPauta) throws JSONException;
}
