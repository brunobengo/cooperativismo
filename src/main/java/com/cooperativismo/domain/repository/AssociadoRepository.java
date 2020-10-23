package com.cooperativismo.domain.repository;

import com.cooperativismo.domain.model.Associado;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssociadoRepository extends MongoRepository<Associado, String> {

}
