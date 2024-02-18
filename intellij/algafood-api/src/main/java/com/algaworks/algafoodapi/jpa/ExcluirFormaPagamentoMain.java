package com.algaworks.algafoodapi.jpa;

import com.algaworks.algafoodapi.AlgafoodApiApplication;
import com.algaworks.algafoodapi.domain.model.FormaPagamento;
import com.algaworks.algafoodapi.domain.repository.FormaPagamentoRepository;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

public class ExcluirFormaPagamentoMain {


  public static void main(String[] args) {

    ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
            .web(WebApplicationType.NONE)
            .run(args);

    FormaPagamentoRepository formaPagamentoRepository = applicationContext.getBean(FormaPagamentoRepository.class);

    FormaPagamento formaPagamento = new FormaPagamento();
    formaPagamento.setId(2L);
    formaPagamentoRepository.remover(formaPagamento);




  }


}
