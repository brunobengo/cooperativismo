package com.cooperativismo.service;

import com.cooperativismo.enums.Voto;
import com.cooperativismo.model.VotoAssembleia;
import com.cooperativismo.negocio.VotoAssembleiaValida;
import com.cooperativismo.repository.VotoAssembleiaRepository;
import org.mockito.InjectMocks;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class VotoAssembleiaService {

    final VotoAssembleiaRepository votoAssembleiaRepository;

    @InjectMocks
    private AssociadoService associadoService;
    @InjectMocks
    private PautaService pautaSevice;

    public VotoAssembleiaService(VotoAssembleiaRepository votoAssembleiaRepository) {
        this.votoAssembleiaRepository = votoAssembleiaRepository;
    }

    public List<VotoAssembleia> findAll() {
        return votoAssembleiaRepository.findAll();
    }

    public VotoAssembleia save(VotoAssembleia votoAssembleia){
        return votoAssembleiaRepository.save(votoAssembleia);
    }

    public boolean existeVotoDoAssociadoNaPauta(String idAssociado, String idPauta){
        return votoAssembleiaRepository.existeVotoDoAssociadoNaPauta(idAssociado, idPauta);
    }

    public void adicionaVoto(String idPauta, String idAssociado, Voto voto){
        VotoAssembleiaValida votoAssembleiaValida =
                new VotoAssembleiaValida(this, associadoService, pautaSevice, idPauta, idAssociado);
        if(votoAssembleiaValida.valida()){
            VotoAssembleia votoAssembleia = new VotoAssembleia();
            votoAssembleia.setIdPauta(idPauta);
            votoAssembleia.setIdAssociado(idAssociado);
            votoAssembleia.setHorarioVoto(LocalDateTime.now());
            votoAssembleia.setVoto(voto == Voto.SIM ? "Sim" : "Não");
            save(votoAssembleia);
        }
    }
}