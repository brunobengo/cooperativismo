package com.cooperativismo.api.controller;

import com.cooperativismo.api.model.ResultadoVotacaoDTO;
import com.cooperativismo.api.model.VotoAssembleiaModel;
import com.cooperativismo.domain.model.Pauta;
import com.cooperativismo.domain.model.VotoAssembleia;
import com.cooperativismo.domain.service.VotoAssembleiaService;
import org.json.JSONException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/assembleia")
public class VotoAssembleiaController {

    private final VotoAssembleiaService votoAssembleiaService;

    @Autowired
    private ModelMapper modelMapper;

    public VotoAssembleiaController(VotoAssembleiaService votoAssembleiaService) {
        this.votoAssembleiaService = votoAssembleiaService;
    }

    @PostMapping(path = "/v1/novovoto")
    @ResponseStatus(HttpStatus.CREATED)
    public VotoAssembleiaModel novovoto(@Validated @RequestBody VotoAssembleiaModel votoAssembleiaModel) throws IOException, JSONException {
        return toModel(votoAssembleiaService.adicionaVoto(votoAssembleiaModel));
    }

    @GetMapping(path = "/v1/resultado/{id}", produces = "application/json")
    public ResponseEntity<ResultadoVotacaoDTO> resultado(@PathVariable String id) {
        return ResponseEntity.ok(votoAssembleiaService.totalVotos(id));
    }

    private VotoAssembleiaModel toModel(VotoAssembleia votoAssembleia) {
        return modelMapper.map(votoAssembleia, VotoAssembleiaModel.class);
    }

    private List<VotoAssembleiaModel> toCollectionModel(List<VotoAssembleia> votosAssembleia) {
        return votosAssembleia.stream()
                .map(votoAssembleia -> toModel(votoAssembleia))
                .collect(Collectors.toList());
    }

    private Pauta toEntity(VotoAssembleiaModel pautaModel) {
        return modelMapper.map(pautaModel, Pauta.class);
    }
}
