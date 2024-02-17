package com.algaworks.algafoodapi.jpa;

import com.algaworks.algafoodapi.AlgafoodApiApplication;
import com.algaworks.algafoodapi.domain.model.Cozinha;
import com.algaworks.algafoodapi.domain.repository.CozinhaRepository;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

public class InclusaoCozinhaMain {


  public static void main(String[] args) {

    ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
            .web(WebApplicationType.NONE)
            .run(args);

    CozinhaRepository cozinhaRepository = applicationContext.getBean(CozinhaRepository.class);

    Cozinha cozinha1 = new Cozinha();
    cozinha1.setNome("Chinesa");

    Cozinha cozinha2 = new Cozinha();
    cozinha2.setNome("Peruana");

    cozinha1 = cozinhaRepository.salvar(cozinha1);
    cozinha2 = cozinhaRepository.salvar(cozinha2);

    System.out.printf("%d - %s\n", cozinha1.getId(), cozinha1.getNome());
    System.out.printf("%d - %s\n", cozinha2.getId(), cozinha2.getNome());


  }


}
