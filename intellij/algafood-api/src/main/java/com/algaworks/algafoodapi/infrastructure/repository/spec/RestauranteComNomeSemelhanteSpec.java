package com.algaworks.algafoodapi.infrastructure.repository.spec;

import com.algaworks.algafoodapi.api.controller.model.Restaurante;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class RestauranteComNomeSemelhanteSpec implements Specification<Restaurante> {

  private String nome;


  public RestauranteComNomeSemelhanteSpec(String nome) {
    this.nome = nome;
  }

  @Override
  public Predicate toPredicate(Root<Restaurante> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
    return builder.like(root.get("nome"), "%" + nome + "%");
  }
}
