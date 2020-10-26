package com.cooperativismo.api.controller;

//import com.cooperativismo.api.model.PautaModel;
import com.cooperativismo.domain.model.Pauta;
import com.cooperativismo.domain.repository.PautaRepository;
import com.cooperativismo.domain.service.PautaService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/pauta")
public class PautaController {

    private final PautaService pautaService;

    @Autowired
    private PautaRepository pautaRepository;

    @Autowired
    private ModelMapper modelMapper;

    public PautaController(PautaService pautaService) {
        this.pautaService = pautaService;
    }

    @GetMapping("/")
    public List<Pauta> listar() {
        return pautaRepository.findAll();
    }

    @PostMapping(path = "/v1/novapauta")
    @ResponseStatus(HttpStatus.CREATED)
    public Pauta novapauta(@Valid @RequestBody Pauta pauta) {
        return pautaRepository.save(pauta);
    }

    @PutMapping("/v1/iniciaassembleia")
    public ResponseEntity<Pauta> iniciaassembleia(@RequestBody Pauta pauta) {
        if (!pautaRepository.existsById(pauta.getId())) {
            return ResponseEntity.notFound().build();
        }
        pautaService.iniciaAssembleia(pauta);
        return ResponseEntity.ok(pauta);
    }

//    private PautaModel toModel(Pauta pauta) {
//        return modelMapper.map(pauta, PautaModel.class);
//    }
//
//    private List<PautaModel> toCollectionModel(List<Pauta> pautas) {
//        return pautas.stream()
//                .map(pauta -> toModel(pauta))
//                .collect(Collectors.toList());
//    }
//
//    private Pauta toEntity(PautaModel pautaModel) {
//        return modelMapper.map(pautaModel, Pauta.class);
//    }
}