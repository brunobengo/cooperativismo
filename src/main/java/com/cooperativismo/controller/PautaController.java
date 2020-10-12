package com.cooperativismo.controller;

import com.cooperativismo.dto.IniciaPautaDTO;
import com.cooperativismo.model.Pauta;
import com.cooperativismo.service.PautaService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pauta")
public class PautaController {
    private final PautaService pautaService;

    public PautaController(PautaService pautaService) {
        this.pautaService = pautaService;
    }

    @PostMapping(path = "/novapauta", consumes = "application/json")
    public Pauta novapauta(@RequestBody Pauta novaPauta){
        return pautaService.save(novaPauta);
    }

    @PutMapping("/{id}/iniciapauta")
    public ResponseEntity iniciapauta(@Validated @RequestBody IniciaPautaDTO iniciaPautaDTO){
        pautaService.iniciaPauta(iniciaPautaDTO);
        return ResponseEntity.ok().build();
    }
}
