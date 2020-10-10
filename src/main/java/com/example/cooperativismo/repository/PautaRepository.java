package com.example.cooperativismo.repository;

import com.example.cooperativismo.document.Pauta;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PautaRepository extends MongoRepository<Pauta, String> {
}
