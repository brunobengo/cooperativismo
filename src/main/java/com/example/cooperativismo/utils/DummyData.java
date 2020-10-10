package com.example.cooperativismo.utils;

import com.example.cooperativismo.document.Associado;
import com.example.cooperativismo.document.Pauta;
import com.example.cooperativismo.document.VotoAssembleia;
import com.example.cooperativismo.repository.AssociadoRepository;
import com.example.cooperativismo.repository.PautaRepository;
import com.example.cooperativismo.repository.VotoAssembleiaRepository;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Component
public class DummyData {
    @Autowired
    AssociadoRepository associadoRepository;
    @Autowired
    PautaRepository pautaRepository;
    @Autowired
    VotoAssembleiaRepository votoAssembleiaRepository;

//    @PostConstruct
    public void saveAssociado(){
        Associado associado = new Associado();
        associado.setCpf("02692471067");
        associadoRepository.save(associado);
    }
//    @PostConstruct
    public void savePauta(){
        Pauta pauta = new Pauta();
        pauta.setDescricao("Nome da rua Tal");
        pauta.setStatusSessao(true);
        pautaRepository.save(pauta);
    }
//    @PostConstruct
    public void saveVoto(){
        VotoAssembleia votoAssembleia = new VotoAssembleia();
        votoAssembleia.setCpfAssociado("02692471067");
        votoAssembleia.setIdPauta("5f813591752e4529ce477083");
        votoAssembleia.setHorarioVoto(LocalDateTime.now());
        votoAssembleia.setVoto("Sim");
        votoAssembleiaRepository.save(votoAssembleia);
    }
}