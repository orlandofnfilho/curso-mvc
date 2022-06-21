package com.gft.gerenciadordeeventos.repository;

import com.gft.gerenciadordeeventos.model.CasaDeShow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CasaDeShowRepository extends JpaRepository<CasaDeShow, Long> {

    Optional<CasaDeShow> findByNomeAndEndereco(String nome, String endereco);
    List<CasaDeShow> findByNomeContainsIgnoreCase(String nome);
}
