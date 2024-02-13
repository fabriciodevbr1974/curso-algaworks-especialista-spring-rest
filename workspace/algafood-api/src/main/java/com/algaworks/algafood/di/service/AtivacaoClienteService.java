package com.algaworks.algafood.di.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.di.modelo.Cliente;
import com.algaworks.algafood.di.notificacao.NivelUrgencia;
import com.algaworks.algafood.di.notificacao.Notificador;
import com.algaworks.algafood.di.notificacao.TipoDoNotificador;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;


//@TipoDoNotificador(value = NivelUrgencia.URGENTE)
//ou
@TipoDoNotificador(NivelUrgencia.URGENTE)

@Component
public class AtivacaoClienteService {

	@TipoDoNotificador(NivelUrgencia.NORMAL)
	@Autowired(required = false)
	private Notificador notificador;
	
	@PostConstruct
	public void init() {
		System.out.println("INIT");
	}

	@PreDestroy
	public void destroy() {
		System.out.println("DESTROY");
	}
	
	

	public void ativar(Cliente cliente) {
		cliente.ativar();

		this.notificador.notificar(cliente, "Seu cadastro no sistema está ativo!");

	}

}
