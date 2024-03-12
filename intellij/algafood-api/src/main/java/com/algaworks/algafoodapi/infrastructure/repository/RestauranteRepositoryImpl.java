package com.algaworks.algafoodapi.infrastructure.repository;

import com.algaworks.algafoodapi.api.controller.model.Restaurante;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

@Repository
public class RestauranteRepositoryImpl implements RestauranteRepositoryQueries {

  @PersistenceContext
  private EntityManager manager;

  @Override
  public List<Restaurante> find(String nome, BigDecimal taxaInicial, BigDecimal taxaFinal) {

    var jpql = new StringBuilder();
    jpql.append("from Restaurante where 0 = 0 ");

    var parametros = new HashMap<String, Object>();

    if (StringUtils.hasLength(nome)) {
      jpql.append("and nome like :nome ");
      parametros.put("nome", "%" + nome + "%");
    }

    if (taxaInicial != null) {
      jpql.append("and taxaFrete >= :taxaFreteInicial ");
      parametros.put("taxaFreteInicial", taxaInicial);
    }

    if (taxaFinal != null) {
      jpql.append("and taxaFrete <= :taxaFreteFinal ");
      parametros.put("taxaFreteFinal", taxaFinal);
    }


    TypedQuery<Restaurante> query = manager.createQuery(jpql.toString(), Restaurante.class);

    parametros.forEach((chave, valor) -> query.setParameter(chave, valor));

    return query.getResultList();
  }


}
