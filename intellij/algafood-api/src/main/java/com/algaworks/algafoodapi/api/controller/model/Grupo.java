package com.algaworks.algafoodapi.api.controller.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Entity
public class Grupo {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(nullable = false)
  private String nome;

  @ManyToMany
  @JoinTable(name = "grupo_permissao",
             joinColumns = @JoinColumn(name = "grupo_id"),
             inverseJoinColumns = @JoinColumn(name = "permissao_id"))
  private List<Permissao> permissoes = new ArrayList<>();


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
    if (o == null || getClass() != o.getClass()) return false;
    Grupo cozinha = (Grupo) o;
    return Objects.equals(id, cozinha.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }
}
