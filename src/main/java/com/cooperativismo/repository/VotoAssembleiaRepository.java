package com.cooperativismo.repository;

import com.cooperativismo.model.VotoAssembleia;
import com.cooperativismo.dto.ResultadoVotacaoDTO;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface VotoAssembleiaRepository extends MongoRepository<VotoAssembleia, String>, VotoAssembleiaRepositoryQuery {

    @Override
    boolean existeVotoDoAssociadoNaPauta(String idAssociado, String idPauta);

    @Override
    LocalDateTime horarioUltimaVotacao(String idPauta);

    @Override
    ResultadoVotacaoDTO totalvotos(String idPauta);
}
