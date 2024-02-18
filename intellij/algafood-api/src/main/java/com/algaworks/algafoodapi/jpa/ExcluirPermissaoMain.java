package com.algaworks.algafoodapi.jpa;

import com.algaworks.algafoodapi.AlgafoodApiApplication;
import com.algaworks.algafoodapi.domain.model.Permissao;
import com.algaworks.algafoodapi.domain.repository.PermissaoRepository;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

public class ExcluirPermissaoMain {


  public static void main(String[] args) {

    ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
            .web(WebApplicationType.NONE)
            .run(args);

    PermissaoRepository permissaoRepository = applicationContext.getBean(PermissaoRepository.class);

    Permissao permissao = new Permissao();
    permissao.setId(2L);
    permissaoRepository.remover(permissao);




  }


}
