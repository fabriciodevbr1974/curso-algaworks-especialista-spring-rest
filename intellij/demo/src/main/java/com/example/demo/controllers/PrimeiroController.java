package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PrimeiroController {

  @Value("${notificador.email.host-servidor}")
  private String host;
  @Value("${notificador.email.porta-servidor}")
  private Integer porta;

  @GetMapping("/hello")
  @ResponseBody
  public String hello(){
    return "Host: " + host + " Porta: " + porta;
  }
}
