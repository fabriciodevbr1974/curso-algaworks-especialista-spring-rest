package com.algaworks.algafoodapi.jpa;

import com.algaworks.algafoodapi.AlgafoodApiApplication;
import com.algaworks.algafoodapi.domain.model.Estado;
import com.algaworks.algafoodapi.domain.repository.EstadoRepository;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

public class InclusaoEstadoMain {


  public static void main(String[] args) {

    ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
            .web(WebApplicationType.NONE)
            .run(args);

    EstadoRepository estadoRepository = applicationContext.getBean(EstadoRepository.class);

    Estado estado1 = new Estado();
    estado1.setNome("Goias");

    Estado estado2 = new Estado();
    estado2.setNome("Distrito Federal");

    estado1 = estadoRepository.salvar(estado1);
    estado2 = estadoRepository.salvar(estado2);

    System.out.printf("%d - %s\n", estado1.getId(), estado1.getNome());
    System.out.printf("%d - %s\n", estado2.getId(), estado2.getNome());


  }


}
