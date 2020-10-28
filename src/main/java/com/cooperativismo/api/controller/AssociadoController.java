package com.cooperativismo.api.controller;

import com.cooperativismo.domain.model.Associado;
import com.cooperativismo.domain.repository.AssociadoRepository;
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

    @GetMapping("/")
    public List<Associado> listar() {
        return toCollectionModel(associadoRepository.findAll());
    }

    @PostMapping(path = "/novoassociado")
    @ResponseStatus(HttpStatus.CREATED)
    public Associado novoassociado(@Valid @RequestBody Associado associado) {
        return toModel(associadoRepository.save(associado));
    }

    @GetMapping("/{associadoId}")
    public ResponseEntity<Associado> buscar(@PathVariable String associadoId) {
        Optional<Associado> associado = associadoRepository.findById(associadoId);

        if (associado.isPresent()) {
            Associado Associado = toModel(associado.get());
            return ResponseEntity.ok(Associado);
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{associadoId}")
    public ResponseEntity<Void> remover(@PathVariable String associadoId) {
        if (!associadoRepository.existsById(associadoId)) {
            return ResponseEntity.notFound().build();
        }
        associadoRepository.deleteById(associadoId);
        return ResponseEntity.noContent().build();
    }

    private Associado toModel(Associado associado) {
        return modelMapper.map(associado, Associado.class);
    }

    private List<Associado> toCollectionModel(List<Associado> associados) {
        return associados.stream()
                .map(associado -> toModel(associado))
                .collect(Collectors.toList());
    }

    private Associado toEntity(Associado Associado) {
        return modelMapper.map(Associado, Associado.class);
    }
}
