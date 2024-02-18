package com.algaworks.algafoodapi.jpa;

import com.algaworks.algafoodapi.AlgafoodApiApplication;
import com.algaworks.algafoodapi.api.controller.model.FormaPagamento;
import com.algaworks.algafoodapi.domain.repository.FormaPagamentoRepository;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

public class InclusaoFormaPagamentoMain {


  public static void main(String[] args) {

    ApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
            .web(WebApplicationType.NONE)
            .run(args);

    FormaPagamentoRepository formaPagamentoRepository = applicationContext.getBean(FormaPagamentoRepository.class);

    FormaPagamento formaPagamento1 = new FormaPagamento();
    formaPagamento1.setDescricao("Dinheiro");

    FormaPagamento formaPagamento2 = new FormaPagamento();
    formaPagamento2.setDescricao("Cartão crédito");

    formaPagamento1 = formaPagamentoRepository.salvar(formaPagamento1);
    formaPagamento2 = formaPagamentoRepository.salvar(formaPagamento2);

    System.out.printf("%d - %s\n", formaPagamento1.getId(), formaPagamento1.getDescricao());
    System.out.printf("%d - %s\n", formaPagamento2.getId(), formaPagamento2.getDescricao());


  }


}
