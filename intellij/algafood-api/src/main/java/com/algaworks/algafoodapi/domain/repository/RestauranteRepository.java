package com.algaworks.algafoodapi.domain.repository;

import com.algaworks.algafoodapi.api.controller.model.Restaurante;

import java.util.List;

public interface RestauranteRepository {

  List<Restaurante> listar();

  Restaurante buscar(Long id);

  Restaurante salvar(Restaurante restaurante);

  void remover(Restaurante restaurante);

}
