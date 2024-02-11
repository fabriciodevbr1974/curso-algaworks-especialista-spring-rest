package com.algaworks.di.service;

import com.algaworks.di.modelo.Cliente;
import com.algaworks.di.notificacao.NotificadorEmail;

public class AtivacaoClienteService {
	
	
	public void ativar(Cliente cliente) {
		cliente.ativar();
		NotificadorEmail notificador = new NotificadorEmail();
		notificador.notificar(cliente, "Seu cadasdastro no sistema est� ativo!");
	}

}
