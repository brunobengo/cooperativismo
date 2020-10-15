package com.cooperativismo.controller;

import com.cooperativismo.dto.ResultadoVotacaoDTO;
import com.cooperativismo.dto.VotoDTO;
import com.cooperativismo.exceptions.BadRequestException;
import com.cooperativismo.service.VotoAssembleiaService;
import org.json.JSONException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/assembleia")
public class VotoAssembleiaController {

    private final VotoAssembleiaService votoAssembleiaService;

    public VotoAssembleiaController(VotoAssembleiaService votoAssembleiaService) {
        this.votoAssembleiaService = votoAssembleiaService;
    }

    @ExceptionHandler(BadRequestException.class)
    @PostMapping(path = "/v1/novovoto", consumes = "application/json")
    public ResponseEntity novovoto(@Validated @RequestBody VotoDTO votoDTO) {
        try {
            votoAssembleiaService.adicionaVoto(votoDTO);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok().build();
    }

    @GetMapping(path = "/v1/resultado/{id}", produces = "application/json")
    public ResponseEntity<ResultadoVotacaoDTO> resultado(@PathVariable String id) {
        return ResponseEntity.ok(votoAssembleiaService.totalVotos(id));
    }
}
