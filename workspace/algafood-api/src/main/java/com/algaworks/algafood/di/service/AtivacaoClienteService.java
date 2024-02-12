package com.algaworks.algafood.di.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.di.modelo.Cliente;
import com.algaworks.algafood.di.notificacao.Notificador;

@Component
public class AtivacaoClienteService {

	@Autowired
	private Notificador notificador;

//	@Autowired //Ponto de injeção no construtor
//	public AtivacaoClienteService(Notificador notificador) {
//		this.notificador = notificador;
//	}

//	public AtivacaoClienteService(String qualquer) {
//		this.notificador = notificador;
//	}

	public void ativar(Cliente cliente) {
		cliente.ativar();
		this.notificador.notificar(cliente, "Seu cadastro no sistema está ativo!");
	}

//	@Autowired //Ponto de injeção no set
//	public void setNotificador(Notificador notificador) {
//		this.notificador = notificador;
//	}

}
