package com.algaworks.algafoodapi.infrastructure.repository;

import com.algaworks.algafoodapi.api.controller.model.Estado;
import com.algaworks.algafoodapi.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafoodapi.domain.repository.EstadoRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Component
public class EstadoRepositoryImpl implements EstadoRepository {
  @PersistenceContext
  private EntityManager manager;
  @Override
  public List<Estado> listar() {
    return manager.createQuery("from Estado", Estado.class).getResultList();
  }

  @Override
  public Estado buscar(Long id) {
    return manager.find(Estado.class, id);
  }

  @Transactional
  @Override
  public Estado salvar(Estado estado) {
    return manager.merge(estado);
  }

  @Transactional
  @Override
  public void remover(Long id) {
    Estado estado = buscar(id);
    if(estado == null){
      throw new EntidadeNaoEncontradaException(String.format("Estado com código %d não encontrado", id));
    }
    manager.remove(estado);
  }
}
