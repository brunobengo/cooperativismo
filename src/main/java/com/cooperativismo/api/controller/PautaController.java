package com.cooperativismo.api.controller;

//import com.cooperativismo.api.model.PautaModel;

import com.cooperativismo.domain.model.Associado;
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
import java.util.Optional;

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

    @PostMapping(path = "/novapauta")
    @ResponseStatus(HttpStatus.CREATED)
    public Pauta novapauta(@Valid @RequestBody Pauta pauta) {
        return pautaRepository.save(pauta);
    }

    @PutMapping("/iniciaassembleia")
    public ResponseEntity<Pauta> iniciaassembleia(@RequestBody Pauta pauta) {
        if (!pautaRepository.existsById(pauta.getId())) {
            return ResponseEntity.notFound().build();
        }
        pautaService.iniciaAssembleia(pauta);
        return ResponseEntity.ok(pauta);
    }

    @GetMapping("/{pautaId}")
    public ResponseEntity<Pauta> buscar(@PathVariable String pautaId) {
        Optional<Pauta> pauta = pautaRepository.findById(pautaId);

        if (pauta.isPresent()) {
            Pauta Pauta = toModel(pauta.get());
            return ResponseEntity.ok(Pauta);
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{pautaId}")
    public ResponseEntity<Void> remover(@PathVariable String pautaId) {
        if (!pautaRepository.existsById(pautaId)) {
            return ResponseEntity.notFound().build();
        }
        pautaRepository.deleteById(pautaId);
        return ResponseEntity.noContent().build();
    }

    private Pauta toModel(Pauta pauta) {
        return modelMapper.map(pauta, Pauta.class);
    }
}