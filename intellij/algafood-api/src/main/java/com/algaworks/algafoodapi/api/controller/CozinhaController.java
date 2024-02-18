package com.algaworks.algafoodapi.api.controller;

import com.algaworks.algafoodapi.api.controller.model.Cozinha;
import com.algaworks.algafoodapi.api.controller.model.CozinhaXmlWrapper;
import com.algaworks.algafoodapi.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafoodapi.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafoodapi.domain.repository.CozinhaRepository;
import com.algaworks.algafoodapi.domain.service.CadastroCozinhaService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cozinhas")
public class CozinhaController {

  @Autowired
  private CozinhaRepository cozinhaRepository;

  @Autowired
  private CadastroCozinhaService cadastroCozinhaService;

  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  public List<Cozinha> listarJSON() {
    return cozinhaRepository.listar();
  }

  @GetMapping(produces = MediaType.APPLICATION_XML_VALUE)
  public CozinhaXmlWrapper listarXML() {
    return new CozinhaXmlWrapper(cozinhaRepository.listar());
  }

  @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Cozinha> buscarJSON(@PathVariable Long id) {

    Cozinha cozinha = cozinhaRepository.buscar(id);
    if (cozinha != null) {
      return ResponseEntity.ok(cozinha);
    }

//    return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    return ResponseEntity.notFound().build();

  }

  @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_XML_VALUE)
  public ResponseEntity<Cozinha> buscarXML(@PathVariable Long id) {
    Cozinha cozinha = cozinhaRepository.buscar(id);

    if (cozinha != null) {
      return ResponseEntity.ok(cozinha);
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
    Cozinha cozinhaAtual = cozinhaRepository.buscar(id);

    if (cozinhaAtual != null) {
//    cozinhaAtual.setNome(cozinha.getNome());
      BeanUtils.copyProperties(cozinha, cozinhaAtual, "id");
      cozinhaRepository.salvar(cozinhaAtual);
      return ResponseEntity.ok(cozinhaAtual);

    }
      return ResponseEntity.notFound().build();


  }
  
  @DeleteMapping("/{cozinhaId}")
//  @ResponseStatus(HttpStatus.NO_CONTENT)
  public ResponseEntity<Cozinha> excluir(@PathVariable Long cozinhaId){
    try {
      cadastroCozinhaService.excluir(cozinhaId);
      return ResponseEntity.noContent().build();

    } catch (EntidadeNaoEncontradaException e){
      return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

    } catch (EntidadeEmUsoException e){
      return ResponseEntity.status(HttpStatus.CONFLICT).build();

    }




  }
  
}
