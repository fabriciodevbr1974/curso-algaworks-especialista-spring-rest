package com.algaworks.algafoodapi.jpa;

import com.algaworks.algafoodapi.AlgafoodApiApplication;
import com.algaworks.algafoodapi.api.controller.model.Cidade;
import com.algaworks.algafoodapi.api.controller.model.Estado;
import com.algaworks.algafoodapi.domain.repository.CidadeRepository;
import com.algaworks.algafoodapi.domain.repository.EstadoRepository;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

public class InclusaoCidade2Main {


  public static void main(String[] args) {

    ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
            .web(WebApplicationType.NONE)
            .run(args);

    CidadeRepository cidadeRepository = applicationContext.getBean(CidadeRepository.class);

    Cidade cidade1 = new Cidade();
    cidade1.setNome("Campo Grande");

    EstadoRepository estadoRepository = applicationContext.getBean(EstadoRepository.class);
    Estado estado = estadoRepository.buscar(5L);
    cidade1.setEstado(estado);


    cidade1 = cidadeRepository.salvar(cidade1);

    System.out.printf("%d - %s\n", cidade1.getId(), cidade1.getNome());


  }


}
