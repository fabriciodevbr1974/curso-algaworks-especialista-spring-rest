package com.algaworks.algafood.di.service;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.algaworks.algafood.di.modelo.Cliente;
import com.algaworks.algafood.di.notificacao.NivelUrgencia;
import com.algaworks.algafood.di.notificacao.Notificador;
import com.algaworks.algafood.di.notificacao.TipoDoNotificador;

//@TipoDoNotificador(value = NivelUrgencia.URGENTE)
//ou
@TipoDoNotificador(NivelUrgencia.URGENTE)

@Component
public class AtivacaoClienteService implements InitializingBean, DisposableBean {

	@TipoDoNotificador(NivelUrgencia.NORMAL)
	@Autowired(required = false)
	private Notificador notificador;

	public void ativar(Cliente cliente) {
		cliente.ativar();

		this.notificador.notificar(cliente, "Seu cadastro no sistema está ativo!");

	}

	@Override
	public void destroy() throws Exception {
		System.out.println("DESTROY");
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println("INIT");
	}

}
