package com.example.cooperativismo.repository;

import com.example.cooperativismo.document.VotoAssembleia;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface VotoAssembleiaRepository extends MongoRepository<VotoAssembleia, String> {
}
