package com.gft.treinamento.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gft.treinamento.entities.Person;

public interface PersonRepository extends JpaRepository<Person, Long> {

}
