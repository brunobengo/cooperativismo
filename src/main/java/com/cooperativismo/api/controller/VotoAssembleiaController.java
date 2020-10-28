package com.cooperativismo.api.controller;

import com.cooperativismo.domain.model.VotoAssembleia;
import com.cooperativismo.domain.repository.VotoAssembleiaRepository;
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

@RestController
@RequestMapping("/assembleia")
public class VotoAssembleiaController {

    private final VotoAssembleiaService votoAssembleiaService;

    @Autowired
    private VotoAssembleiaRepository votoAssembleiaRepository;

    @Autowired
    private ModelMapper modelMapper;

    public VotoAssembleiaController(VotoAssembleiaService votoAssembleiaService) {
        this.votoAssembleiaService = votoAssembleiaService;
    }

    @GetMapping("/")
    public List<VotoAssembleia> listar() {
        return votoAssembleiaRepository.findAll();
    }


    @PostMapping(path = "/novovoto")
    @ResponseStatus(HttpStatus.CREATED)
    public VotoAssembleia novovoto(@Validated @RequestBody VotoAssembleia votoAssembleia) throws IOException, JSONException {
        return votoAssembleiaService.adicionaVoto(votoAssembleia);
    }

    @DeleteMapping("/{votoAssembleiaId}")
    public ResponseEntity<Void> remover(@PathVariable String votoAssembleiaId) {
        if (!votoAssembleiaRepository.existsById(votoAssembleiaId)) {
            return ResponseEntity.notFound().build();
        }
        votoAssembleiaRepository.deleteById(votoAssembleiaId);
        return ResponseEntity.noContent().build();
    }

    //TODO arrumar isso aii
//    @GetMapping(path = "/resultado/{id}")
//    public ResponseEntity<ResultadoVotacaoDTO> resultado(@PathVariable String id) {
//        return ResponseEntity.ok(votoAssembleiaService.totalVotos(id));
//    }
}
