package com.algaworks.algafood.di.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.di.modelo.Cliente;

//@TipoDoNotificador(value = NivelUrgencia.URGENTE)
//ou
//@TipoDoNotificador(NivelUrgencia.URGENTE)

@Component
public class AtivacaoClienteService  {

//	@TipoDoNotificador(NivelUrgencia.SEM_URGENCIA)
//	@Autowired(required = false)
//	private Notificador notificador;
	
	@Autowired
	private ApplicationEventPublisher eventPublisher;

	public void ativar(Cliente cliente) {
		cliente.ativar();

//		this.notificador.notificar(cliente, "Seu cadastro no sistema está ativo!");
		System.out.println("AtivacaoClienteService");
		eventPublisher.publishEvent(new ClienteAtivadoEvent(cliente));
		System.out.println("AtivacaoClienteService");

	}

	

}
