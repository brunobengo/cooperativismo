package com.cooperativismo.controller;

import com.cooperativismo.enums.Voto;
import com.cooperativismo.service.VotoAssembleiaService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cooperativismo/voto")
public class VotoAssembleiaController {

    private final VotoAssembleiaService votoAssembleiaService;

    public VotoAssembleiaController(VotoAssembleiaService votoAssembleiaService) {
        this.votoAssembleiaService = votoAssembleiaService;
    }

    @PostMapping("/novovoto")
    public void novovoto(@PathVariable String idPauta, @PathVariable String idAssociado,
                                   @PathVariable boolean confirma) {
        votoAssembleiaService.adicionaVoto(idPauta, idAssociado, confirma? Voto.SIM : Voto.NÃO);
    }
}
