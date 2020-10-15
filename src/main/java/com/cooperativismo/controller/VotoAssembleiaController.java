package com.cooperativismo.controller;

import com.cooperativismo.dto.ResultadoVotacaoDTO;
import com.cooperativismo.dto.VotoDTO;
import com.cooperativismo.exceptions.InternalServerErrorException;
import com.cooperativismo.model.VotoAssembleia;
import com.cooperativismo.service.VotoAssembleiaService;
import org.json.JSONException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/assembleia")
public class VotoAssembleiaController {

    private final VotoAssembleiaService votoAssembleiaService;

    public VotoAssembleiaController(VotoAssembleiaService votoAssembleiaService) {
        this.votoAssembleiaService = votoAssembleiaService;
    }

    @ExceptionHandler(InternalServerErrorException.class)
    @PostMapping(path = "/v1/novovoto", consumes = "application/json")
    public ResponseEntity novovoto(@Validated @RequestBody VotoDTO votoDTO) throws IOException, JSONException {
        if(votoAssembleiaService.associadoDesabilitadoParaVoto(votoDTO.getIdAssociado())){
            throw new InternalServerErrorException("Associado está desabilitado para voto.");
        }else if(votoAssembleiaService.associadoJaVotouNaPauta(votoDTO.getIdAssociado(), votoDTO.getIdPauta())){
            throw new InternalServerErrorException("Associado já votou na pauta.");
        }else if(votoAssembleiaService.pautaJaFechou(votoDTO.getIdPauta())) {
            throw new InternalServerErrorException("Pauta está fechada.");
        }else if(votoAssembleiaService.pautaIsInativa(votoDTO.getIdPauta())){
            throw new InternalServerErrorException("Pauta foi fechada por inatividade.");
        }else{
            votoAssembleiaService.adicionaVoto(votoDTO);
            return ResponseEntity.ok().build();
        }
    }

    @GetMapping(path = "/v1/resultado/{id}", produces = "application/json")
    public ResponseEntity<ResultadoVotacaoDTO> resultado(@PathVariable String id) {
        return ResponseEntity.ok(votoAssembleiaService.totalVotos(id));
    }
}
