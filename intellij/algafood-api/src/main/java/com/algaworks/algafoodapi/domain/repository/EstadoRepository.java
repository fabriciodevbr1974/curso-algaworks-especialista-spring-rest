package com.algaworks.algafoodapi.domain.repository;

import com.algaworks.algafoodapi.api.controller.model.Estado;

import java.util.List;

public interface EstadoRepository {

  List<Estado> listar();

  Estado buscar(Long id);

  Estado salvar(Estado estado);

  void remover(Long id);

}
