package com.algaworks.di.service;

import com.algaworks.di.modelo.Cliente;
import com.algaworks.di.notificacao.NotificadorSMS;

public class AtivacaoClienteService {
	
	
	public void ativar(Cliente cliente) {
		cliente.ativar();
		NotificadorSMS notificador = new NotificadorSMS();
		notificador.notificar(cliente, "Seu cadasdastro no sistema está ativo!");
	}

}
