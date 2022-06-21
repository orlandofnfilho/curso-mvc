package com.gft.gerenciadordeeventos.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class CasaDeShow {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Nome é obrigátorio.")
    @Size(min = 5, max = 100, message = "O nome deve ter no mínimo 5 e no máximo 100 caracteres.")
    private String nome;

    @NotEmpty(message = "O endereço é obrigatório.")
    @Size(min = 5, max = 150, message = "O endereço deve ter no mínimo 5 e no máximo 150 caracteres.")
    private String endereco;
}
