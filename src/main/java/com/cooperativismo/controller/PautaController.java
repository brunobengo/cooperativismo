package com.cooperativismo.controller;

import com.cooperativismo.dto.IniciaPautaDTO;
import com.cooperativismo.model.Pauta;
import com.cooperativismo.service.PautaService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/pauta")
public class PautaController {
    private final PautaService pautaService;

    public PautaController(PautaService pautaService) {
        this.pautaService = pautaService;
    }

    @PostMapping(path = "/v1/novapauta", consumes = "application/json")
    public Pauta novapauta(@RequestBody Pauta novaPauta) {
        return pautaService.save(novaPauta);
    }

    @PutMapping("/v1/iniciaassembleia")
    public ResponseEntity iniciaassembleia(@Validated @RequestBody IniciaPautaDTO iniciaPautaDTO) {
        pautaService.iniciaassembleia(iniciaPautaDTO);
        return ResponseEntity.ok().build();
    }
}
