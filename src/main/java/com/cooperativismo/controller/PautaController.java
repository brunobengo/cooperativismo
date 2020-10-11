package com.cooperativismo.controller;

import com.cooperativismo.model.Pauta;
import com.cooperativismo.service.PautaService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cooperativismo/pauta")
public class PautaController {
    private final PautaService pautaService;

    public PautaController(PautaService pautaService) {
        this.pautaService = pautaService;
    }

    @PostMapping("/novapauta")
    public Pauta novapauta(@RequestBody Pauta novaPauta){
        return pautaService.save(novaPauta);
    }

    @PutMapping("/iniciavotacao/{id}")
    public Pauta iniciavotacao(@RequestBody Pauta newPauta, @PathVariable String idPauta, @PathVariable int duracao){
        return pautaService.abreSessaoDeVotacao(idPauta, duracao);
            //TODO ver o que é ResponseEntity
    }

}
