package com.cooperativismo.service;

import com.cooperativismo.dto.ResultadoVotacaoDTO;
import com.cooperativismo.dto.VotoDTO;
import com.cooperativismo.enums.Voto;
import com.cooperativismo.exceptions.BadRequestException;
import com.cooperativismo.model.VotoAssembleia;
import com.cooperativismo.repository.VotoAssembleiaRepository;
import com.cooperativismo.valida.VotoAssembleiaValida;
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

    public void adicionaVoto(VotoDTO votoDTO){
        VotoAssembleiaValida votoAssembleiaValida =
                new VotoAssembleiaValida(this, associadoService, pautaSevice, votoDTO);
        if (votoAssembleiaValida.valida()) {
            VotoAssembleia votoAssembleia = new VotoAssembleia();
            votoAssembleia.setIdPauta(votoDTO.getIdPauta());
            votoAssembleia.setIdAssociado(votoDTO.getIdAssociado());
            votoAssembleia.setHorarioVoto(LocalDateTime.now());
            votoAssembleia.setVoto(votoDTO.getVoto() == Voto.SIM ? "Sim" : "Não");
            save(votoAssembleia);
        } else {
            throw new BadRequestException("Voto invalidado.");
        }
    }

    public LocalDateTime horarioUltimaVotacao(String idPauta){
        return votoAssembleiaRepository.horarioUltimaVotacao(idPauta);
    }

    public ResultadoVotacaoDTO totalVotos(String idPauta){
        return votoAssembleiaRepository.totalvotos(idPauta);
    }
}