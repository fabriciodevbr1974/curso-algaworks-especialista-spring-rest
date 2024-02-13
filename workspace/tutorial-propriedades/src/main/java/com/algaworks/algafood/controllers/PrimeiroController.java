package com.algaworks.algafood.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PrimeiroController {
	
	@Value("${notificacao.email.host-servidor}")
	private String host;

	@GetMapping("/primeiro")
	@ResponseBody
	public String mensagem() {
		return host;
	}
	
	
}
