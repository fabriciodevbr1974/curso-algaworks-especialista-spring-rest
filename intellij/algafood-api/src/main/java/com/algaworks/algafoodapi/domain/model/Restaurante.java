package com.algaworks.algafoodapi.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.math.BigDecimal;
import java.util.Objects;

@Entity
public class Restaurante {

  @Id
  private Long id;

  private String nome;

  @Column(name = "taxa_frete")
  private BigDecimal taxaFrete;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public BigDecimal getTaxaFrete() {
    return taxaFrete;
  }

  public void setTaxaFrete(BigDecimal taxaFrete) {
    this.taxaFrete = taxaFrete;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Restaurante that)) return false;

    return Objects.equals(id, that.id);
  }

  @Override
  public int hashCode() {
    return id != null ? id.hashCode() : 0;
  }
}
