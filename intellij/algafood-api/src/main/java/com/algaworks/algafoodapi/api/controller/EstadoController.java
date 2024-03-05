package com.algaworks.algafoodapi.api.controller;

import com.algaworks.algafoodapi.api.controller.model.Estado;
import com.algaworks.algafoodapi.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafoodapi.domain.repository.EstadoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/estados")
public class EstadoController {

  @Autowired
  private EstadoRepository estadoRepository;

  @GetMapping
  public List<Estado> listar() {
    return estadoRepository.findAll();
  }

  @GetMapping("/{estadoId}")
  public ResponseEntity<Estado> buscar(@PathVariable Long estadoId) {

    Optional<Estado> estado = estadoRepository.findById(estadoId);
    if (estado.isEmpty()) {
      return ResponseEntity.notFound().build();
    }

    return ResponseEntity.ok(estado.get());
  }

  @PostMapping
  public ResponseEntity<Estado> adicionar(@RequestBody Estado estado) {
    estado = estadoRepository.save(estado);
    return ResponseEntity.status(HttpStatus.CREATED).body(estado);
  }

  @PutMapping("/{estadoId}")
  public ResponseEntity<?> adicionar(@PathVariable Long estadoId, @RequestBody Estado estado) {

    Optional<Estado> estadoAtual = estadoRepository.findById(estadoId);
    if (estadoAtual.isEmpty()) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(String.format("Estado com código %d não encontrado", estadoId));
    }

    Estado estadoAlterado = estadoAtual.get();

    BeanUtils.copyProperties(estado, estadoAlterado, "id");
    estadoAlterado = estadoRepository.save(estadoAlterado);
    return ResponseEntity.ok(estadoAlterado);


  }

  @DeleteMapping("/{estadoId}")
  public ResponseEntity excluir(@PathVariable Long estadoId) {
    try {
      estadoRepository.deleteById(estadoId);
      return ResponseEntity.noContent().build();
    } catch (EntidadeNaoEncontradaException e){
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }
  }

}
