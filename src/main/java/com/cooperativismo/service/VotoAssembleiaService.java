package com.cooperativismo.service;

import com.cooperativismo.model.VotoAssembleia;
import com.cooperativismo.repository.PautaRepository;
import com.cooperativismo.repository.VotoAssembleiaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VotoAssembleiaService {

    final VotoAssembleiaRepository votoAssembleiaRepository;

    public VotoAssembleiaService(VotoAssembleiaRepository votoAssembleiaRepository) {
        this.votoAssembleiaRepository = votoAssembleiaRepository;
    }

    public Optional<VotoAssembleia> findById(String id){
        return votoAssembleiaRepository.findById(id);
    }

    public VotoAssembleia save(VotoAssembleia votoAssembleia){
        return votoAssembleiaRepository.save(votoAssembleia);
    }

    public void delete(VotoAssembleia votoAssembleia){
        votoAssembleiaRepository.delete(votoAssembleia);
    }
}