package com.cooperativismo.controller;

import com.cooperativismo.model.Associado;
import com.cooperativismo.service.AssociadoService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/associado")
public class AssociadoController {

    private final AssociadoService associadoService;

    public AssociadoController(AssociadoService associadoService) {
        this.associadoService = associadoService;
    }

    @PostMapping(path = "/v1/novoassociado")
    public Associado novoassociado(@RequestBody Associado novoAssociado) {
        return associadoService.save(novoAssociado);
    }
}
