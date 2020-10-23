package com.cooperativismo.domain.service;

import com.cooperativismo.api.model.ResultadoVotacaoDTO;
import com.cooperativismo.api.model.VotoAssembleiaModel;
import com.cooperativismo.domain.exceptions.NegocioException;
import com.cooperativismo.domain.repository.AssociadoRepository;
import com.cooperativismo.domain.repository.PautaRepository;
import com.cooperativismo.domain.enums.HabilitacaoParaVoto;
import com.cooperativismo.domain.externo.HabiitacaoVoto;
import com.cooperativismo.domain.model.Associado;
import com.cooperativismo.domain.model.Pauta;
import com.cooperativismo.domain.model.VotoAssembleia;
import com.cooperativismo.domain.repository.VotoAssembleiaRepository;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.OffsetDateTime;
import java.util.List;

@Service
public class VotoAssembleiaService {

    final VotoAssembleiaRepository votoAssembleiaRepository;

    @Autowired
    private AssociadoRepository associadoRepository;
    @Autowired
    private PautaRepository pautaRepository;

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

    public VotoAssembleia adicionaVoto(VotoAssembleiaModel votoAssembleiaModel) throws IOException, JSONException {
        if(associadoDesabilitadoParaVoto(votoAssembleiaModel.getIdAssociado())){
            throw new NegocioException("Associado está desabilitado para voto.");
        }else if(associadoJaVotouNaPauta(votoAssembleiaModel.getIdAssociado(), votoAssembleiaModel.getIdPauta())){
            throw new NegocioException("Associado já votou na pauta.");
        }else if(pautaJaFechou(votoAssembleiaModel.getIdPauta())) {
            throw new NegocioException("Pauta está fechada.");
        }else if(pautaIsInativa(votoAssembleiaModel.getIdPauta())){
            throw new NegocioException("Pauta foi fechada por inatividade.");
        }else{
            VotoAssembleia votoAssembleia = new VotoAssembleia()
                    .setIdPauta(votoAssembleiaModel.getIdPauta())
                    .setIdAssociado(votoAssembleiaModel.getIdAssociado())
                    .setHorarioVoto(OffsetDateTime.now())
                    .setVoto(votoAssembleiaModel.isConfirma() ? "Sim" : "Não");
            return votoAssembleiaRepository.save(votoAssembleia);
        }
    }

    public OffsetDateTime horarioUltimaVotacao(String idPauta){
        return votoAssembleiaRepository.horarioUltimaVotacao(idPauta);
    }

    public ResultadoVotacaoDTO totalVotos(String idPauta){
        return votoAssembleiaRepository.totalvotos(idPauta);
    }

    private boolean pautaIsInativa(String idPauta) {
        Pauta pauta = pautaRepository.findById(idPauta)
                .orElseThrow(() -> new NegocioException("Pauta não encontrda"));
        OffsetDateTime horarioUltimaVotacao = horarioUltimaVotacao(pauta.getId());
        boolean isInativa = OffsetDateTime.now().minusMinutes(1).isAfter(horarioUltimaVotacao);
        if(isInativa){
            pauta.setStatusSessao(false);
            pautaRepository.save(pauta);
        }
        return isInativa;
    }

    private boolean pautaJaFechou(String idPauta) {
        Pauta pauta = pautaRepository.findById(idPauta)
                .orElseThrow(() -> new NegocioException("Pauta não encontrda"));
        OffsetDateTime momentoDeFechamentoDaSecao =
                pauta.getHoraAberturaAssembleia().plusMinutes(pauta.getMinutosDeDuracaoDaSessao());
        boolean jaFechou = OffsetDateTime.now().isAfter(momentoDeFechamentoDaSecao);
        if(jaFechou){
            pauta.setStatusSessao(false);
            pautaRepository.save(pauta);
        }
        return jaFechou;
    }

    private boolean associadoDesabilitadoParaVoto(String idAsssociado) throws IOException, JSONException {
        Associado associado = associadoRepository.findById(idAsssociado)
                .orElseThrow(() -> new NegocioException("Associado não encontrado"));
        return !(new HabiitacaoVoto().isDisponivel(associado.getCpf())
                == HabilitacaoParaVoto.HABILITADO);
    }

    private boolean associadoJaVotouNaPauta(String idAssociado, String idPauta){
        return existeVotoDoAssociadoNaPauta(idAssociado, idPauta);
    }
}