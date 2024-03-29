package com.algaworks.algafoodapi.api.controller;

import com.algaworks.algafoodapi.api.controller.model.Cozinha;
import com.algaworks.algafoodapi.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafoodapi.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafoodapi.domain.repository.CozinhaRepository;
import com.algaworks.algafoodapi.domain.service.CadastroCozinhaService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cozinhas")
public class CozinhaController {

  @Autowired
  private CozinhaRepository cozinhaRepository;

  @Autowired
  private CadastroCozinhaService cadastroCozinhaService;

  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public List<Cozinha> listarJSON() {
    return cozinhaRepository.findAll();
  }


  @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Cozinha> buscarJSON(@PathVariable Long id) {

    Optional<Cozinha> cozinha = cozinhaRepository.findById(id);
    if (cozinha.isPresent()) {
      return ResponseEntity.ok(cozinha.get());
    }

//    return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    return ResponseEntity.notFound().build();

  }




  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Cozinha adicionar(@RequestBody Cozinha cozinha) {
    return cadastroCozinhaService.salvar(cozinha);
  }

  @PutMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity<Cozinha> atualizar(@PathVariable Long id, @RequestBody Cozinha cozinha) {
    Optional<Cozinha> cozinhaAtual = cozinhaRepository.findById(id);

    if (cozinhaAtual.isPresent()) {
      BeanUtils.copyProperties(cozinha, cozinhaAtual.get(), "id");
      Cozinha cozinhaSalva = cadastroCozinhaService.salvar(cozinhaAtual.get());
      return ResponseEntity.ok(cozinhaSalva);
    }

    return ResponseEntity.notFound().build();
  }

  @DeleteMapping("/{cozinhaId}")
//  @ResponseStatus(HttpStatus.NO_CONTENT)
  public ResponseEntity<Cozinha> excluir(@PathVariable Long cozinhaId) {
    try {
      cadastroCozinhaService.excluir(cozinhaId);
      return ResponseEntity.noContent().build();

    } catch (EntidadeNaoEncontradaException e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

    } catch (EntidadeEmUsoException e) {
      return ResponseEntity.status(HttpStatus.CONFLICT).build();

    }


  }

}
