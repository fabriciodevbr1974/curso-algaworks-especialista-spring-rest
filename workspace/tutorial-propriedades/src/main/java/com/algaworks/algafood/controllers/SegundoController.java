package com.algaworks.algafood.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.algaworks.algafood.services.NotificacaoService;

@Controller
public class SegundoController {
	
	@Autowired
	private NotificacaoService notificacaoService;

	@GetMapping("/segundo")
	@ResponseBody
	public String mensagem() {
		return notificacaoService.getHostServidor();
	}
	
	
}
