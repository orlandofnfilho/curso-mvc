package com.gft.gerenciadordeeventos.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GeneroMusical {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Nome é obrigatório.")
    @Size(min = 5, max = 100, message = "O nome deve ter no mínimo 5 e no máximo 100 caracteres.")
    private String nome;

    @NotBlank(message = "A url da imagem é obrigatória.")
    private String urlImagem;
}
