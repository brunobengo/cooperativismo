package com.cooperativismo.dummy;

import com.cooperativismo.dto.IniciaPautaDTO;
import com.cooperativismo.dto.VotoDTO;
import com.cooperativismo.enums.Voto;
import com.cooperativismo.model.Associado;
import com.cooperativismo.model.Pauta;
import com.cooperativismo.service.AssociadoService;
import com.cooperativismo.service.PautaService;
import com.cooperativismo.service.VotoAssembleiaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
@Component
public class DummyData {

//    @Autowired
//    AssociadoService associadoService;
//    @Autowired
//    PautaService pautaService;
//    @Autowired
//    VotoAssembleiaService votoAssembleiaService;
//
//    @PostConstruct
//    public void grava(){
////        pautaService.iniciaassembleia(new IniciaPautaDTO().setDuracao(60).setIdPauta("5f84ca60f225d36bb020d1eb"));
////        try {
////            votoAssembleiaService.adicionaVoto(new VotoDTO().setIdPauta("5f84ca60f225d36bb020d1eb")
////                    .setIdAssociado("5f84c83701716613dd25bac8")
////                    .setVoto(Voto.SIM));
////            votoAssembleiaService.totalVotos("5f84ca60f225d36bb020d1eb");
////        }catch(Exception e){
////            e.printStackTrace();
////        }
//    }
}