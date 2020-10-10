package com.example.cooperativismo.repository;

import com.example.cooperativismo.document.Associado;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AssociadoRepository extends MongoRepository<Associado, String> {
}
