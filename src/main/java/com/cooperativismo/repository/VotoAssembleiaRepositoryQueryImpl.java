package com.cooperativismo.repository;

import com.cooperativismo.dto.ResultadoVotacaoDTO;
import com.cooperativismo.model.VotoAssembleia;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.GroupOperation;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.time.LocalDateTime;
import java.util.List;

public class VotoAssembleiaRepositoryQueryImpl implements VotoAssembleiaRepositoryQuery{

    private final MongoTemplate mongoTemplate;
    public VotoAssembleiaRepositoryQueryImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public boolean existeVotoDoAssociadoNaPauta(String idAssociado, String idPauta) {
        List<VotoAssembleia> listVotoAssembleia = mongoTemplate.find(
            Query.query(
                Criteria.where("votoassembleia.idpauta").is(new ObjectId(idPauta))
            ).addCriteria(
                Criteria.where("votoassembleia.idAssociado").is(new ObjectId(idAssociado))
            ),
            VotoAssembleia.class
        );
        if(listVotoAssembleia != null){
           return listVotoAssembleia.size() > 0;
        }
        return false;
    }

    @Override
    public LocalDateTime horarioUltimaVotacao(String idPauta) {
//        pegar o date time da última votacao
//            pegar votacoes, filtrar pela última
//        List<VotoAssembleia> listHorarioUltimaVotacao = mongoTemplate.find(
//            Query.query(
//                Criteria.where("votoassembleia.idpauta").is(new ObjectId(idPauta))
//            )
//            ,VotoAssembleia.class
//        );
        return null;
    }

    @Override
    public ResultadoVotacaoDTO totalvotos(String idPauta) {
        MatchOperation match = new MatchOperation(Criteria.where("idpauta").is(new ObjectId(idPauta)));
        GroupOperation group = Aggregation.group("voto").count().as("total");
        Aggregation aggregate = Aggregation.newAggregation(match, group);

        AggregationResults<VotoAssembleia> orderAggregate = mongoTemplate.aggregate(aggregate,
                "votoassembleia", VotoAssembleia.class);

        ResultadoVotacaoDTO ResultadoVotacaoDTO = new ResultadoVotacaoDTO() ;
        ResultadoVotacaoDTO.setVotosSim(orderAggregate.getRawResults().getInteger("sim"));
        ResultadoVotacaoDTO.setVotosNao(orderAggregate.getRawResults().getInteger("não"));

        return ResultadoVotacaoDTO;
    }


}
