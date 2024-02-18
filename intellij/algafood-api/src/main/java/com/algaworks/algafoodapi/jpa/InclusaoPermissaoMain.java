package com.algaworks.algafoodapi.jpa;

import com.algaworks.algafoodapi.AlgafoodApiApplication;
import com.algaworks.algafoodapi.domain.model.Permissao;
import com.algaworks.algafoodapi.domain.repository.PermissaoRepository;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

public class InclusaoPermissaoMain {


  public static void main(String[] args) {

    ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
            .web(WebApplicationType.NONE)
            .run(args);

    PermissaoRepository permissaoRepository = applicationContext.getBean(PermissaoRepository.class);

    Permissao permissao1 = new Permissao();
    permissao1.setNome("Chinesa");
    permissao1.setDescricao("Chinesa");

    Permissao permissao2 = new Permissao();
    permissao2.setNome("Peruana");
    permissao2.setDescricao("Peruana");

    permissao1 = permissaoRepository.salvar(permissao1);
    permissao2 = permissaoRepository.salvar(permissao2);

    System.out.printf("%d - %s\n", permissao1.getId(), permissao1.getNome());
    System.out.printf("%d - %s\n", permissao2.getId(), permissao2.getNome());


  }


}
