package com.algaworks.algafoodapi.domain.repository;

import com.algaworks.algafoodapi.api.controller.model.Estado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface EstadoRepository extends JpaRepository<Estado, Long> {



}
