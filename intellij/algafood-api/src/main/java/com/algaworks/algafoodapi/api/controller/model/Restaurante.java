package com.algaworks.algafoodapi.api.controller.model;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Restaurante {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String nome;

  @Column(name = "taxa_frete", nullable = false)
  private BigDecimal taxaFrete;

//  @JsonIgnore
  @ManyToOne
  @JoinColumn(name = "cozinha_id", nullable = false)
  private Cozinha cozinha;

  @JsonIgnore
  @ManyToMany
  @JoinTable(name = "restaurante_forma_pagamento",
             joinColumns = @JoinColumn(name = "restaurante_id"),
             inverseJoinColumns = @JoinColumn(name = "forma_pagamento_id"))
  private List<FormaPagamento> formasPagamento = new ArrayList<>();

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

  public Cozinha getCozinha() {
    return cozinha;
  }

  public void setCozinha(Cozinha cozinha) {
    this.cozinha = cozinha;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Restaurante that = (Restaurante) o;
    return Objects.equals(id, that.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }
}
