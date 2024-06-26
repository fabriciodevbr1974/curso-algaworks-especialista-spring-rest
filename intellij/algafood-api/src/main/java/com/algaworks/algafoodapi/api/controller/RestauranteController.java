package com.algaworks.algafoodapi.api.controller;

import com.algaworks.algafoodapi.api.controller.model.Restaurante;
import com.algaworks.algafoodapi.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafoodapi.domain.repository.RestauranteRepository;
import com.algaworks.algafoodapi.domain.service.CadastroRestauranteService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {

  @Autowired
  private RestauranteRepository restauranteRepository;

  @Autowired
  private CadastroRestauranteService cadastroRestauranteService;


  @GetMapping
  public ResponseEntity<List<Restaurante>> listar() {
    return ResponseEntity.ok(restauranteRepository.findAll());
  }

  @GetMapping("/{restauranteId}")
  public ResponseEntity<Restaurante> buscar(@PathVariable Long restauranteId) {
    Optional<Restaurante> restaurante = restauranteRepository.findById(restauranteId);
    if (restaurante.isPresent()) {
      return ResponseEntity.ok(restaurante.get());
    }
    return ResponseEntity.notFound().build();
  }


  @GetMapping("/por-nome-frete")
  public List<Restaurante> porNomeFrete(String nome, BigDecimal taxaFreteInicial, BigDecimal taxaFreteFinal) {
    return restauranteRepository.find(nome, taxaFreteInicial, taxaFreteFinal);

  }


  @PostMapping
  public ResponseEntity<?> adicionar(@RequestBody Restaurante restaurante) {

    try {
      restaurante = cadastroRestauranteService.salvar(restaurante);
      return ResponseEntity.status(HttpStatus.CREATED).body(restaurante);
    } catch (EntidadeNaoEncontradaException e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

  @PutMapping("/{restauranteId}")
  public ResponseEntity<?> atualizar(@PathVariable Long restauranteId,
                                     @RequestBody Restaurante restaurante) {
    try {
      Restaurante restauranteAtual = restauranteRepository.findById(restauranteId).orElse(null);

      if (restauranteAtual != null) {
        BeanUtils.copyProperties(restaurante, restauranteAtual, "id", "formasPagamento", "endereco",
                "dataCadastro");

        restauranteAtual = cadastroRestauranteService.salvar(restauranteAtual);
        return ResponseEntity.ok(restauranteAtual);
      }

      return ResponseEntity.notFound().build();

    } catch (EntidadeNaoEncontradaException e) {
      return ResponseEntity.badRequest()
              .body(e.getMessage());
    }
  }

  @PatchMapping("/{restauranteId}")
  public ResponseEntity<?> atualizarParcial(@PathVariable Long restauranteId,
                                            @RequestBody Map<String, Object> campos) {
    Optional<Restaurante> restauranteAtual = restauranteRepository.findById(restauranteId);

    if (restauranteAtual.isEmpty()) {
      return ResponseEntity.notFound().build();
    }

    Restaurante restauranteAlterado = restauranteAtual.get();

    merge(campos, restauranteAlterado);

    return atualizar(restauranteId, restauranteAlterado);
  }

  private void merge(Map<String, Object> dadosOrigem, Restaurante restauranteDestino) {
    ObjectMapper objectMapper = new ObjectMapper();
    Restaurante restauranteOrigem = objectMapper.convertValue(dadosOrigem, Restaurante.class);

    dadosOrigem.forEach((nomePropriedade, valorPropriedade) -> {
      Field field = ReflectionUtils.findField(Restaurante.class, nomePropriedade);
      field.setAccessible(true);

      Object novoValor = ReflectionUtils.getField(field, restauranteOrigem);

//			System.out.println(nomePropriedade + " = " + valorPropriedade + " = " + novoValor);

      ReflectionUtils.setField(field, restauranteDestino, novoValor);
    });
  }


}
