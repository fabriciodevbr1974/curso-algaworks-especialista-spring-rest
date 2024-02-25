package com.algaworks.algafoodapi.api.controller;

import com.algaworks.algafoodapi.api.controller.model.Restaurante;
import com.algaworks.algafoodapi.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafoodapi.domain.repository.RestauranteRepository;
import com.algaworks.algafoodapi.domain.service.CadastroRestauranteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {

  @Autowired
  private RestauranteRepository restauranteRepository;

  @Autowired
  private CadastroRestauranteService cadastroRestauranteService;


  @GetMapping
  public ResponseEntity<List<Restaurante>> listar() {
    return ResponseEntity.ok(restauranteRepository.listar());
  }

  @GetMapping("/{restauranteId}")
  public ResponseEntity<Restaurante> buscar(@PathVariable Long restauranteId) {
    Restaurante restaurante = restauranteRepository.buscar(restauranteId);
    if(restaurante != null){
      return ResponseEntity.ok(restaurante);
    }
    return  ResponseEntity.notFound().build();
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public ResponseEntity<?> adicionar(@RequestBody Restaurante restaurante){

    try {
      restaurante = cadastroRestauranteService.salvar(restaurante);
      return ResponseEntity.status(HttpStatus.CREATED).body(restaurante);
    } catch (EntidadeNaoEncontradaException e){
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }


}
