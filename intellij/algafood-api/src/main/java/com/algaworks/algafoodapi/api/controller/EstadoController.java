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

@RestController
@RequestMapping("/estados")
public class EstadoController {

  @Autowired
  private EstadoRepository estadoRepository;

  @GetMapping
  public List<Estado> listar() {
    return estadoRepository.listar();
  }

  @GetMapping("/{estadoId}")
  public ResponseEntity<Estado> buscar(@PathVariable Long estadoId) {

    Estado estado = estadoRepository.buscar(estadoId);
    if (estado == null) {
      return ResponseEntity.notFound().build();
    }

    return ResponseEntity.ok(estado);
  }

  @PostMapping
  public ResponseEntity<Estado> adicionar(@RequestBody Estado estado) {
    estado = estadoRepository.salvar(estado);
    return ResponseEntity.status(HttpStatus.CREATED).body(estado);
  }

  @PutMapping("/{estadoId}")
  public ResponseEntity<?> adicionar(@PathVariable Long estadoId, @RequestBody Estado estado) {

    Estado estadoAtual = estadoRepository.buscar(estadoId);
    if (estadoAtual == null) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(String.format("Estado com código %d não encontrado", estadoId));
    }

    BeanUtils.copyProperties(estado, estadoAtual, "id");
    estadoAtual = estadoRepository.salvar(estadoAtual);
    return ResponseEntity.ok(estadoAtual);


  }

  @DeleteMapping("/{estadoId}")
  public ResponseEntity excluir(@PathVariable Long estadoId) {
    try {
      estadoRepository.remover(estadoId);
      return ResponseEntity.noContent().build();
    } catch (EntidadeNaoEncontradaException e){
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }
  }

}
