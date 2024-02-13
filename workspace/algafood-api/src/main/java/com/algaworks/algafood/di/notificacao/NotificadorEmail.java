package com.algaworks.algafood.di.notificacao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.di.modelo.Cliente;

//@Profile("prod")
@TipoDoNotificador(NivelUrgencia.SEM_URGENCIA)
@Component
public class NotificadorEmail implements Notificador {

//	public NotificadorEmail() {
//		System.out.println("NotificadorEmail REAL");
//	}
	
	@Autowired
	private NotificadorProperties notificadorProperties;

	@Override
	public void notificar(Cliente cliente, String mensagem) {
		
		System.out.println(notificadorProperties.getHost());
		System.out.println(notificadorProperties.getPorta());

		System.out.printf("Notificando %s através do e-mail %s: %s\n", cliente.getNome(), cliente.getEmail(), mensagem);
	}

}
