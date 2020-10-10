package com.cooperativismo.utils;

import com.cooperativismo.model.Associado;
import com.cooperativismo.model.Pauta;
import com.cooperativismo.model.VotoAssembleia;
import com.cooperativismo.repository.AssociadoRepository;
import com.cooperativismo.repository.PautaRepository;
import com.cooperativismo.repository.VotoAssembleiaRepository;
import com.cooperativismo.service.AssociadoService;
import com.cooperativismo.service.PautaService;
import com.cooperativismo.service.VotoAssembleiaService;
import org.mockito.Mock;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class DummyData {
    @Mock
    AssociadoService associadoService;
    @Mock
    PautaService pautaService;
    @Mock
    VotoAssembleiaService votoAssembleiaService;


//    @PostConstruct
    public void saveAssociado(){
        Associado associado = new Associado();
        associado.setCpf("02692471067");
        associadoService.save(associado);
    }
//    @PostConstruct
    public void savePauta(){
        Pauta pauta = new Pauta();
        pauta.setDescricao("Nome da rua Tal");
        pauta.abreSessao(60);
        pautaService.save(pauta);
    }
//    @PostConstruct
    public void saveVoto(){
        VotoAssembleia votoAssembleia = new VotoAssembleia();
        votoAssembleia.adicionaVoto("5f813591752e4529ce477083",
                "asdhada123iadji12312",
                VotoAssembleia.Voto.SIM);
        votoAssembleiaService.save(votoAssembleia);
    }
}