package com.cooperativismo.controller;

import com.cooperativismo.model.Pauta;
import com.cooperativismo.service.PautaService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.time.LocalDate;

@RequestMapping("/pauta")
@RestController
public class PautaController {
    private final PautaService pautaService;

    public PautaController(PautaService pautaService) {
        this.pautaService = pautaService;
    }

    @PostMapping(consumes = "application/json")
    public ResponseEntity criaPauta(@Valid Pauta pauta, BindingResult result, RedirectAttributes attributes){
        String id = pautaService.save(pauta).getId();
        URI location = UriComponentsBuilder.fromUriString("pauta")
                .path("/{id}").buildAndExpand(id).toUri();
        return ResponseEntity.created(location).build();
    }

    

}
