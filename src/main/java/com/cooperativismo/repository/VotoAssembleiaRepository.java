package com.cooperativismo.repository;

import com.cooperativismo.model.VotoAssembleia;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VotoAssembleiaRepository extends MongoRepository<VotoAssembleia, String> {
}
