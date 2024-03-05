package com.algaworks.algafoodapi.api.controller;

import com.algaworks.algafoodapi.api.controller.model.Cidade;
import com.algaworks.algafoodapi.api.controller.model.Estado;
import com.algaworks.algafoodapi.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafoodapi.domain.repository.CidadeRepository;
import com.algaworks.algafoodapi.domain.repository.EstadoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cidades")
public class CidadeController {

  @Autowired
  private CidadeRepository cidadeRepository;

  @Autowired
  private EstadoRepository estadoRepository;

  @GetMapping
  public List<Cidade> listar() {
    return cidadeRepository.findAll();
  }

  @GetMapping("/{cidadeId}")
  public ResponseEntity<Cidade> buscar(@PathVariable Long cidadeId) {

    Optional<Cidade> cidade = cidadeRepository.findById(cidadeId);
    if (cidade.isEmpty()) {
      return ResponseEntity.notFound().build();
    }

    return ResponseEntity.ok(cidade.get());
  }

  @PostMapping
  public ResponseEntity<?> adicionar(@RequestBody Cidade cidade) {

    Long estadoId = cidade.getEstado().getId();
    Optional<Estado> estado = estadoRepository.findById(estadoId);
    if (estado.isEmpty()) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(String.format("Estado de código %d não encontrado", estadoId));
    }


    cidade = cidadeRepository.save(cidade);
    return ResponseEntity.status(HttpStatus.CREATED).body(cidade);
  }

  @PutMapping("/{cidadeId}")
  public ResponseEntity<?> atualizar(@PathVariable Long cidadeId, @RequestBody Cidade cidade) {

    Optional<Cidade> cidadeAtual = cidadeRepository.findById(cidadeId);
    if (cidadeAtual.isEmpty()) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(String.format("Cidade com código %d não encontrado", cidadeId));
    }

    Long estadoId = cidade.getEstado().getId();
    Optional<Estado> estado = estadoRepository.findById(estadoId);
    if (estado.isEmpty()) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(String.format("Estado de código %d não encontrado", estadoId));
    }
    Cidade cidadeAlterada = cidadeAtual.get();
    BeanUtils.copyProperties(cidade, cidadeAlterada, "id");
    cidadeAlterada.setEstado(estado.get());
    cidadeAlterada = cidadeRepository.save(cidadeAlterada);
    return ResponseEntity.ok(cidadeAlterada);

  }

  @DeleteMapping("/{cidadeId}")
  public ResponseEntity excluir(@PathVariable Long cidadeId) {
    try {
      cidadeRepository.deleteById(cidadeId);
      return ResponseEntity.noContent().build();
    } catch (EntidadeNaoEncontradaException e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }
  }

}
