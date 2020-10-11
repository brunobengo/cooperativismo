package com.cooperativismo.controller;

import com.cooperativismo.enums.Voto;
import com.cooperativismo.model.VotoAssembleia;
import com.cooperativismo.service.VotoAssembleiaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RequestMapping("/voto")
@RestController
public class VotoAssembleiaController {

    private final VotoAssembleiaService votoAssembleiaService;

    public VotoAssembleiaController(VotoAssembleiaService votoAssembleiaService) {
        this.votoAssembleiaService = votoAssembleiaService;
    }

    @RequestMapping(value = "/votos", method = RequestMethod.GET)
    public ModelAndView getPosts(){
        ModelAndView mv  = new ModelAndView("posts");
        List<VotoAssembleia> posts = votoAssembleiaService. findAll();
        mv.addObject("posts", posts);
        return mv;
    }

    @PostMapping(path = "votar", consumes = "application/json")
    public ResponseEntity votar(@PathVariable String idPauta, @PathVariable String idAssociado, @PathVariable boolean confirma) throws Exception {
        votoAssembleiaService.adicionaVoto(idPauta, idAssociado, confirma? Voto.SIM : Voto.NÃO);

        URI location = UriComponentsBuilder.fromUriString("pauta")
                .path("/{id}").buildAndExpand(idPauta).toUri();
        return ResponseEntity.created(location).build();
    }
}
