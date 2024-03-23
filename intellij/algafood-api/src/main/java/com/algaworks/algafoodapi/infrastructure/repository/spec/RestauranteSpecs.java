package com.algaworks.algafoodapi.infrastructure.repository.spec;

import com.algaworks.algafoodapi.api.controller.model.Restaurante;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;

public class RestauranteSpecs {


  public static Specification<Restaurante> comFreteGratis() {
//    return new RestauranteComFreteGratisSpec();
    return (root, query, builder) -> builder.equal(root.get("taxaFrete"), BigDecimal.ZERO);
  }

  public static Specification<Restaurante> comNomeSemelhante(String nome) {
//    return new RestauranteComNomeSemelhanteSpec(nome);
    return (root, query, builder) -> builder.like(root.get("nome"), "%" + nome + "%");
  }


}
