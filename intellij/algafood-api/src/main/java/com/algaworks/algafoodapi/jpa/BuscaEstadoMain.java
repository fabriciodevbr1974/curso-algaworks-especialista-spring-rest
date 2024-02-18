package com.algaworks.algafoodapi.jpa;

import com.algaworks.algafoodapi.AlgafoodApiApplication;
import com.algaworks.algafoodapi.api.controller.model.Estado;
import com.algaworks.algafoodapi.domain.repository.EstadoRepository;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

public class BuscaEstadoMain {


  public static void main(String[] args) {

    ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
            .web(WebApplicationType.NONE)
            .run(args);

    EstadoRepository estadoRepository = applicationContext.getBean(EstadoRepository.class);

    Estado estado = estadoRepository.buscar(1L);

    System.out.println(estado.getNome());
  }


}
