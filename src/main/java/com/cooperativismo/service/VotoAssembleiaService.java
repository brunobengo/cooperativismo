package com.cooperativismo.service;

import com.cooperativismo.dto.ResultadoVotacaoDTO;
import com.cooperativismo.dto.VotoDTO;
import com.cooperativismo.enums.HabilitacaoParaVoto;
import com.cooperativismo.exceptions.InternalServerErrorException;
import com.cooperativismo.externo.HabiitacaoVoto;
import com.cooperativismo.model.Associado;
import com.cooperativismo.model.Pauta;
import com.cooperativismo.model.VotoAssembleia;
import com.cooperativismo.repository.VotoAssembleiaRepository;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class VotoAssembleiaService {

    final VotoAssembleiaRepository votoAssembleiaRepository;

    @Autowired
    private AssociadoService associadoService;
    @Autowired
    private PautaService pautaService;

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

    public void adicionaVoto(VotoDTO votoDTO) {
        VotoAssembleia votoAssembleia = new VotoAssembleia()
                .setIdPauta(votoDTO.getIdPauta())
                .setIdAssociado(votoDTO.getIdAssociado())
                .setHorarioVoto(LocalDateTime.now())
                .setVoto(votoDTO.isConfirma() ? "Sim" : "Não");
        save(votoAssembleia);
    }

    public LocalDateTime horarioUltimaVotacao(String idPauta){
        return votoAssembleiaRepository.horarioUltimaVotacao(idPauta);
    }

    public ResultadoVotacaoDTO totalVotos(String idPauta){
        return votoAssembleiaRepository.totalvotos(idPauta);
    }

    public boolean pautaIsInativa(String idPauta) {
        Pauta pauta = pautaService.findById(idPauta);
        LocalDateTime horarioUltimaVotacao = horarioUltimaVotacao(pauta.getId());
        boolean isInativa = LocalDateTime.now().minusMinutes(1).isAfter(horarioUltimaVotacao);
        if(isInativa){
            pauta.setStatusSessao(false);
            pautaService.save(pauta);
        }
        return isInativa;
    }

    public boolean pautaJaFechou(String idPauta) {
        Pauta pauta = pautaService.findById(idPauta);
        LocalDateTime momentoDeFechamentoDaSecao =
                pauta.getHoraAberturaAssembleia().plusMinutes(pauta.getMinutosDeDuracaoDaSessao());
        boolean jaFechou = LocalDateTime.now().isAfter(momentoDeFechamentoDaSecao);
        if(jaFechou){
            pauta.setStatusSessao(false);
            pautaService.save(pauta);
        }
        return jaFechou;
    }

    public boolean associadoDesabilitadoParaVoto(String idAsssociado) throws IOException, JSONException {
        Associado associado = associadoService.find(idAsssociado);
        return !(new HabiitacaoVoto().isDisponivel(associado.getCpf())
                == HabilitacaoParaVoto.HABILITADO);
    }

    public boolean associadoJaVotouNaPauta(String idAssociado, String idPauta){
        return existeVotoDoAssociadoNaPauta(idAssociado, idPauta);
    }
}