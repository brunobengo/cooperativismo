package com.cooperativismo.domain.repository;

import com.cooperativismo.domain.model.Pauta;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PautaRepository extends MongoRepository<Pauta, String> {

}