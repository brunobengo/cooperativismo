package com.cooperativismo.domain.repository;

import com.cooperativismo.api.model.ResultadoVotacaoDTO;
import com.cooperativismo.domain.enums.Voto;
import com.cooperativismo.domain.model.VotoAssembleia;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.*;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.time.OffsetDateTime;
import java.util.List;


public class VotoAssembleiaRepositoryQueryImpl implements VotoAssembleiaRepositoryQuery {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public boolean existeVotoDoAssociadoNaPauta(String idAssociado, String idPauta) {
        List<VotoAssembleia> listVotoAssembleia = mongoTemplate.find(
            new Query(Criteria.where("idPauta").is(idPauta)
            ).addCriteria(Criteria.where("idAssociado").is(idAssociado)
            ), VotoAssembleia.class
        );
        if (listVotoAssembleia != null) {
            return listVotoAssembleia.size() > 0;
        }
        return false;
    }

    @Override
    public OffsetDateTime horarioUltimaVotacao(String idPauta) {
        List<VotoAssembleia> listVotoAssembleia = mongoTemplate.find(
                new Query(Criteria.where("idPauta").is(idPauta)), VotoAssembleia.class
        );
        if (listVotoAssembleia != null && listVotoAssembleia.size() > 0) {
            return listVotoAssembleia.get(listVotoAssembleia.size()-1).getHorarioVoto();
        }
        return OffsetDateTime.now();
    }

    @Override
    public ResultadoVotacaoDTO totalvotos(String idPauta) throws JSONException {
        ResultadoVotacaoDTO resultadoVotacaoDTO = new ResultadoVotacaoDTO() ;
        resultadoVotacaoDTO.setIdPauta(idPauta);
        resultadoVotacaoDTO.setVotosSim(getVotos(idPauta, Voto.SIM));
        resultadoVotacaoDTO.setVotosNao(getVotos(idPauta, Voto.NÃO));
        return resultadoVotacaoDTO;
    }

    private int getVotos(String idPauta, Voto tipoVoto) throws JSONException {
        MatchOperation match = new MatchOperation(Criteria.where("idPauta").is(idPauta)
                        .and("voto").is(tipoVoto == Voto.SIM ? "Sim" : "Não"));
        GroupOperation group =  Aggregation.group().count().as("count");
        AggregationResults<VotoAssembleia> orderAggregate = mongoTemplate.aggregate(
                Aggregation.newAggregation(match, group),"votoAssembleia", VotoAssembleia.class);
        JSONObject jsonObject = new JSONObject(orderAggregate.getRawResults().toJson());
        JSONArray arrayResults = new JSONArray(jsonObject.get("results").toString());
        if(arrayResults.length() > 0){
            JSONObject results = new JSONObject(arrayResults.get(0).toString());
            return results.getInt("count");
        }else{
            return 0;
        }
    }


}
