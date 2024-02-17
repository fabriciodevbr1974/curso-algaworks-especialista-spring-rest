package com.algaworks.algafoodapi.domain.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "tab_cozinhas")
public class Cozinha {

  @Id
  private Long id;

  @Column(name = "nom_cozinha")
  private String nome;

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

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Cozinha cozinha)) return false;
    return id.equals(cozinha.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }
}
