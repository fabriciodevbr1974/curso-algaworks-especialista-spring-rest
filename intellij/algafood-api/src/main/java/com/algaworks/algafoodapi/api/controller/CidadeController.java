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

@RestController
@RequestMapping("/cidades")
public class CidadeController {

  @Autowired
  private CidadeRepository cidadeRepository;

  @Autowired
  private EstadoRepository estadoRepository;

  @GetMapping
  public List<Cidade> listar() {
    return cidadeRepository.listar();
  }

  @GetMapping("/{cidadeId}")
  public ResponseEntity<Cidade> buscar(@PathVariable Long cidadeId) {

    Cidade cidade = cidadeRepository.buscar(cidadeId);
    if (cidade == null) {
      return ResponseEntity.notFound().build();
    }

    return ResponseEntity.ok(cidade);
  }

  @PostMapping
  public ResponseEntity<?> adicionar(@RequestBody Cidade cidade) {

    Long estadoId = cidade.getEstado().getId();
    Estado estado = estadoRepository.buscar(estadoId);
    if(estado == null){
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(String.format("Estado de código %d não encontrado", estadoId));
    }


    cidade = cidadeRepository.salvar(cidade);
    return ResponseEntity.status(HttpStatus.CREATED).body(cidade);
  }

  @PutMapping("/{cidadeId}")
  public ResponseEntity<?> atualizar(@PathVariable Long cidadeId, @RequestBody Cidade cidade) {

    Cidade cidadeAtual = cidadeRepository.buscar(cidadeId);
    if (cidadeAtual == null) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(String.format("Cidade com código %d não encontrado", cidadeId));
    }

    Long estadoId = cidade.getEstado().getId();
    Estado estado = estadoRepository.buscar(estadoId);
    if(estado == null){
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(String.format("Estado de código %d não encontrado", estadoId));
    }

    BeanUtils.copyProperties(cidade, cidadeAtual, "id");
    cidadeAtual.setEstado(estado);
    cidadeAtual = cidadeRepository.salvar(cidadeAtual);
    return ResponseEntity.ok(cidadeAtual);

  }

  @DeleteMapping("/{cidadeId}")
  public ResponseEntity excluir(@PathVariable Long cidadeId) {
    try {
      cidadeRepository.remover(cidadeId);
      return ResponseEntity.noContent().build();
    } catch (EntidadeNaoEncontradaException e){
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }
  }

}
