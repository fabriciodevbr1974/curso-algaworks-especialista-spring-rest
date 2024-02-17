package com.algaworks.algafoodapi.jpa;

import com.algaworks.algafoodapi.AlgafoodApiApplication;
import com.algaworks.algafoodapi.domain.model.Restaurante;
import com.algaworks.algafoodapi.domain.repository.RestauranteRepository;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import java.math.BigDecimal;

public class InclusaoRestauranteMain {


  public static void main(String[] args) {

    ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
            .web(WebApplicationType.NONE)
            .run(args);

    RestauranteRepository restauranteRepository = applicationContext.getBean(RestauranteRepository.class);

    Restaurante restaurante1 = new Restaurante();
    restaurante1.setNome("Mangueiras Cervejaria");
    restaurante1.setTaxaFrete(BigDecimal.valueOf(2.57));

    Restaurante restaurante2 = new Restaurante();
    restaurante2.setNome("Pimentas");
    restaurante2.setTaxaFrete(BigDecimal.valueOf(3.33));

    restaurante1 = restauranteRepository.salvar(restaurante1);
    restaurante2 = restauranteRepository.salvar(restaurante2);

    System.out.printf("%d - %s\n", restaurante1.getId(), restaurante1.getNome());
    System.out.printf("%d - %s\n", restaurante2.getId(), restaurante2.getNome());


  }


}
