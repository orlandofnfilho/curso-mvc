package com.gft.gerenciadordeeventos.repository;

import com.gft.gerenciadordeeventos.model.GeneroMusical;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GeneroMusicalRepostiroy extends JpaRepository<GeneroMusical, Long> {

    Optional<GeneroMusical> findByNome(String nome);
    Optional<List<GeneroMusical>> findByNomeContains(String nome);
}
