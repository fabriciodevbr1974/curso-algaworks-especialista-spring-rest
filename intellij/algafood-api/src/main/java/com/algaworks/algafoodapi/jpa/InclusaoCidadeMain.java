package com.algaworks.algafoodapi.jpa;

import com.algaworks.algafoodapi.AlgafoodApiApplication;
import com.algaworks.algafoodapi.domain.model.Cidade;
import com.algaworks.algafoodapi.domain.model.Estado;
import com.algaworks.algafoodapi.domain.repository.CidadeRepository;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

public class InclusaoCidadeMain {


  public static void main(String[] args) {

    ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
            .web(WebApplicationType.NONE)
            .run(args);

    CidadeRepository cidadeRepository = applicationContext.getBean(CidadeRepository.class);

    Cidade cidade1 = new Cidade();
    cidade1.setNome("Goiania");

    Cidade cidade2 = new Cidade();
    cidade2.setNome("Brasilia");

    cidade1 = cidadeRepository.salvar(cidade1);
    cidade2 = cidadeRepository.salvar(cidade2);

    System.out.printf("%d - %s\n", cidade1.getId(), cidade1.getNome());
    System.out.printf("%d - %s\n", cidade2.getId(), cidade2.getNome());


  }


}