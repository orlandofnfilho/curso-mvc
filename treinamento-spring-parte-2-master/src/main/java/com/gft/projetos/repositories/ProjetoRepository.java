package com.gft.projetos.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gft.projetos.entities.Projeto;

public interface ProjetoRepository extends JpaRepository<Projeto, Long> {

}
