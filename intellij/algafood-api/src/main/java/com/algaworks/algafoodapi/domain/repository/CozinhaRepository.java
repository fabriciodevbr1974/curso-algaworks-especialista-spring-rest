package com.algaworks.algafoodapi.domain.repository;

import com.algaworks.algafoodapi.api.controller.model.Cozinha;

import java.util.List;

public interface CozinhaRepository {

  List<Cozinha> listar();

  List<Cozinha> consultarPorNome(String nome);


  Cozinha buscar(Long id);

  Cozinha salvar(Cozinha cozinha);

  void remover(Long id);

}
