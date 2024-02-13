package com.algaworks.algafood.di.notificacao;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("notificador.email")
public class NotificadorProperties {

	private String host;
	private Integer porta = 25;

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public Integer getPorta() {
		return porta;
	}

	public void setPorta(Integer porta) {
		this.porta = porta;
	}

}
