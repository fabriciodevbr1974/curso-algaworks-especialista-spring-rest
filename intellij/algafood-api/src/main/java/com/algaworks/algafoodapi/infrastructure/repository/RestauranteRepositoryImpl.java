package com.algaworks.algafoodapi.infrastructure.repository;

import com.algaworks.algafoodapi.domain.model.Restaurante;
import com.algaworks.algafoodapi.domain.repository.RestauranteRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Component
public class RestauranteRepositoryImpl implements RestauranteRepository {
  @PersistenceContext
  private EntityManager manager;
  @Override
  public List<Restaurante> listar() {
    return manager.createQuery("from Restaurante", Restaurante.class).getResultList();
  }

  @Override
  public Restaurante buscar(Long id) {
    return manager.find(Restaurante.class, id);
  }

  @Transactional
  @Override
  public Restaurante salvar(Restaurante cozinha) {
    return manager.merge(cozinha);
  }

  @Transactional
  @Override
  public void remover(Restaurante cozinha) {
    cozinha = buscar(cozinha.getId());
    manager.remove(cozinha);
  }
}