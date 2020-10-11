package com.cooperativismo.repository;


import com.cooperativismo.model.VotoAssembleia;
import com.mongodb.client.model.Filters;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.mapreduce.GroupBy;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static com.mongodb.client.model.Aggregates.match;
import static com.mongodb.client.model.Aggregates.unwind;

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
        List<VotoAssembleia> listHorarioUltimaVotacao = mongoTemplate.find(
            Query.query(
                Criteria.where("votoassembleia.idpauta").is(new ObjectId(idPauta))
            ).with(new Sort(Direction.DESC, "votoassembleia.horarioVoto"))
            ,VotoAssembleia.class
        );
        if(listHorarioUltimaVotacao != null){
//            return listHorarioUltimaVotacao.size() > 0;
        }
//        return false;

        return null;
    }

    @Override
    public List<VotoAssembleia> totalvotos(String idPauta) {
        Aggregation agg = new Aggregation(
//
//                unwind("voto"),
//                project("_id")
//                        .and("details.student._id").as("sid")
//                        .and("details.studentStatus.statusCode").as("statuscode"),
//                group("sid", "statuscode")
//                        .count().as("total");
//        List<VotoAssembleia> listVotoAssembleia = mongoTemplate
//                .aggregate(agg, "votoassembleia", VotoAssembleia.class);   .
//            .group(
//                    Criteria.where("votoassembleia.idpauta").is(new ObjectId(idPauta)),
//                    "votoassembleia",
//                        GroupBy.key("voto"),
//                    .class,
//
//            )
//
//
//
//                find(
//            Query.query(
//                Criteria.where("votoassembleia.idpauta").is(new ObjectId(idPauta))
//            ).,
//            VotoAssembleia.class
//        );
        return listVotoAssembleia;
    }
}
