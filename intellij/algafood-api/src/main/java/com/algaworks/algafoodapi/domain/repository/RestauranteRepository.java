package com.algaworks.algafoodapi.domain.repository;

import com.algaworks.algafoodapi.api.controller.model.Restaurante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface RestauranteRepository extends JpaRepository<Restaurante, Long> {

  List<Restaurante> findByTaxaFreteBetween(BigDecimal taxaInicial, BigDecimal taxaFinal);
  List<Restaurante> findByNomeContainingAndCozinhaId(String nome, Long cozinha);
  Optional<Restaurante> findFirstByNomeContaining(String nome); //Retorna o primeiro registro da lista (findFirst).
  List<Restaurante> findTop2ByNomeContaining(String nome);//Retoran os dois primeiros registros da lista (findTop2).

  int countByCozinhaId(Long cozinha);

}
