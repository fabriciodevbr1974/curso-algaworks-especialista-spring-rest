package com.algaworks.algafoodapi.domain.repository;

import com.algaworks.algafoodapi.api.controller.model.Permissao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@Repository
public interface PermissaoRepository  extends JpaRepository<Permissao, Long> {



}
