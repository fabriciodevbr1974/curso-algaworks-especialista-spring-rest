package com.algaworks.algafoodapi.api.controller.model;

import javax.persistence.*;
import java.util.Objects;


@Entity
public class Cidade {


  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(nullable = false)
  private String nome;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "estado_id", nullable = false)
  private Estado estado;


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


  public Estado getEstado() {
    return estado;
  }

  public void setEstado(Estado estado) {
    this.estado = estado;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Cidade cozinha = (Cidade) o;
    return Objects.equals(id, cozinha.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }
}
