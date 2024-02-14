package com.algaworks.algafood.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PrimeiroController {
	
	@Value("${notificador.email.host-servidor}")
	private String hostServidor;
	
	@Value("${notificador.email.porta-servidor}")
	private Integer portaServidor;
	
	@GetMapping("/hello")
	@ResponseBody
	public String enviar() {
		return "Host: " + hostServidor + " Porta: " + portaServidor;
	}

}
