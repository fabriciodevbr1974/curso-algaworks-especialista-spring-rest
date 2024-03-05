package com.algaworks.algafoodapi.domain.repository;

import com.algaworks.algafoodapi.api.controller.model.Cozinha;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CozinhaRepository extends JpaRepository<Cozinha, Long> {

  List<Cozinha> findTodosByNomeContaining(String nome);

  Optional<Cozinha> findByNome(String nome);

  boolean existsByNome(String nome);//Consulta exata



}
