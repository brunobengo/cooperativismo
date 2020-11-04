package com.cooperativismo.domain.repository;

import com.cooperativismo.api.model.ResultadoVotacaoDTO;
import com.cooperativismo.domain.model.VotoAssembleia;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.OffsetDateTime;
import java.util.Optional;

@Repository
public interface VotoAssembleiaRepository extends MongoRepository<VotoAssembleia, String>, VotoAssembleiaRepositoryQuery {

    @Override
    boolean existeVotoDoAssociadoNaPauta(String idAssociado, String idPauta);

    @Override
    OffsetDateTime horarioUltimaVotacao(String idPauta);

    @Override
    Optional<ResultadoVotacaoDTO> totalvotos(String idPauta);

}
