package com.algaworks.di.service;

import com.algaworks.di.modelo.Cliente;
import com.algaworks.di.modelo.Produto;
import com.algaworks.di.notificacao.NotificadorEmail;

public class EmissaoNotaFiscalService {
	
	public void emitir(Cliente cliente, Produto produto) {
		//TODO emitir a nota fiscal aqui...
	
		NotificadorEmail notificador = new NotificadorEmail();
		notificador.notificar(cliente, "Nota fiscal do produto " + produto.getNome() + " foi emitida!");
	
	
	}

}
