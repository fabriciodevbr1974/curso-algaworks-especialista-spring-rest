package com.algaworks.algafoodapi.api.controller;

import com.algaworks.algafoodapi.api.controller.model.Cozinha;
import com.algaworks.algafoodapi.domain.repository.CozinhaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/teste")
public class TesteController {

  @Autowired
  private CozinhaRepository cozinhaRepository;


  //  @GetMapping("/cozinha/por-nome")
//  public List<Cozinha> cozinhaPorNome(String nome) {
//    return cozinhaRepository.findQualquerCoisaByNome(nome);
//  }
//
  @GetMapping("/cozinha/por-nome")
  public Optional<Cozinha> cozinhaPorNome(String nome) {
    return cozinhaRepository.findByNome(nome);
  }


}
