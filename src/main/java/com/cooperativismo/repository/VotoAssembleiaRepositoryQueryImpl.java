package com.cooperativismo.repository;

import com.cooperativismo.dto.ResultadoVotacaoDTO;
import com.cooperativismo.enums.Voto;
import com.cooperativismo.model.VotoAssembleia;
import org.bson.types.ObjectId;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.*;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.time.LocalDateTime;
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
    public LocalDateTime horarioUltimaVotacao(String idPauta) {
        MatchOperation matchStage = Aggregation.match(Criteria.where("idPauta").is(idPauta));
        SortOperation sort = Aggregation.sort(Sort.Direction.DESC, "horarioVoto");

        Aggregation agg = Aggregation.newAggregation(VotoAssembleia.class, matchStage, sort);

        AggregationResults<VotoAssembleia> aggregationVotoAssembleia
                = mongoTemplate.aggregate(agg, "votoassembleia", VotoAssembleia.class);
        List<VotoAssembleia> listVotoAssembleia = aggregationVotoAssembleia.getMappedResults();

        if (listVotoAssembleia != null && listVotoAssembleia.size() > 0) {
            return listVotoAssembleia.get(0).getHorarioVoto();
        }
        return LocalDateTime.now();
    }

    @Override
    public ResultadoVotacaoDTO totalvotos(String idPauta) throws JSONException {
        ResultadoVotacaoDTO resultadoVotacaoDTO = new ResultadoVotacaoDTO() ;
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
