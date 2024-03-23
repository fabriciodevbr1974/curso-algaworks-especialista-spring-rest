package com.algaworks.algafoodapi.api.controller;

import com.algaworks.algafoodapi.api.controller.model.Cozinha;
import com.algaworks.algafoodapi.api.controller.model.Restaurante;
import com.algaworks.algafoodapi.domain.repository.CozinhaRepository;
import com.algaworks.algafoodapi.domain.repository.RestauranteRepository;
import com.algaworks.algafoodapi.infrastructure.repository.spec.RestauranteSpecs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;


@RestController
@RequestMapping("/teste")
public class TesteController {

  @Autowired
  private CozinhaRepository cozinhaRepository;

  @Autowired
  private RestauranteRepository restauranteRepository;


  @GetMapping("/cozinha/por-nome")
  public List<Cozinha> cozinhaPorNome(String nome) {
    return cozinhaRepository.findTodosByNomeContaining(nome);
  }


  @GetMapping("/restaurantes/por-taxa-frete")
  public List<Restaurante> restaurantesPorTaxaFrete(BigDecimal taxaInicial, BigDecimal taxaFinal) {
    return restauranteRepository.findByTaxaFreteBetween(taxaInicial, taxaFinal);
  }

  @GetMapping("/restaurantes/por-nome-e-cozinha-id")
  public List<Restaurante> restaurantesPorNomeECozinha(String nome, Long cozinhaId) {
    return restauranteRepository.findByNomeContainingAndCozinhaId(nome, cozinhaId);
  }


  //  @GetMapping("/cozinha/por-nome")
//  public Optional<Cozinha> cozinhaPorNome(String nome) {
//    return cozinhaRepository.findByNome(nome);
//  }

  @GetMapping("/restaurantes/com-frete-gratis")
  public List<Restaurante> restaurantesComFreteGratis(String nome) {
    return restauranteRepository.findAll(RestauranteSpecs.comFreteGratis().and(RestauranteSpecs.comNomeSemelhante(nome)));
  }


}
