package com.algaworks.algafoodapi.api.controller.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import java.util.List;

@JacksonXmlRootElement(localName = "cozinhas")
public class CozinhaXmlWrapper {

  @JsonProperty("cozinha")
  @JacksonXmlElementWrapper(useWrapping = false)
  private List<Cozinha> cozinhas;

  public CozinhaXmlWrapper(List<Cozinha> cozinhas) {
    this.cozinhas = cozinhas;
  }

  public List<Cozinha> getCozinhas() {
    return cozinhas;
  }

  public void setCozinhas(List<Cozinha> cozinhas) {
    this.cozinhas = cozinhas;
  }
}
