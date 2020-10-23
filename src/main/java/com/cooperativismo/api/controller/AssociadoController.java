package com.cooperativismo.api.controller;

import com.cooperativismo.api.model.AssociadoModel;
import com.cooperativismo.domain.model.Associado;
import com.cooperativismo.domain.repository.AssociadoRepository;
import com.cooperativismo.domain.service.AssociadoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/associado")
public class AssociadoController {

    @Autowired
    private AssociadoRepository associadoRepository;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public List<AssociadoModel> listar() {
        return toCollectionModel(associadoRepository.findAll());
    }

    @PostMapping(path = "/v1/novoassociado")
    @ResponseStatus(HttpStatus.CREATED)
    public AssociadoModel novoassociado(@Valid @RequestBody AssociadoModel associadoModel) {
        Associado associado = toEntity(associadoModel);
        return toModel(associadoRepository.save(associado));
    }

    @GetMapping("/{associadoId}")
    public ResponseEntity<AssociadoModel> buscar(@PathVariable String associadoId) {
        Optional<Associado> associado = associadoRepository.findById(associadoId);

        if (associado.isPresent()) {
            AssociadoModel associadoModel = toModel(associado.get());
            return ResponseEntity.ok(associadoModel);
        }

        return ResponseEntity.notFound().build();
    }

    private AssociadoModel toModel(Associado associado) {
        return modelMapper.map(associado, AssociadoModel.class);
    }

    private List<AssociadoModel> toCollectionModel(List<Associado> associados) {
        return associados.stream()
                .map(associado -> toModel(associado))
                .collect(Collectors.toList());
    }

    private Associado toEntity(AssociadoModel associadoModel) {
        return modelMapper.map(associadoModel, Associado.class);
    }
}
