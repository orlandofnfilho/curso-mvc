package com.gft.gerenciadordeeventos.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Evento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Nome é obrigatório.")
    private String nome;

    @NotNull(message = "A capcidade do evento é obrigatória.")
    @Min(value = 0, message = "O mínimo é um.")
    private Integer capacidade;

    @NotNull(message = "O preço do ingresso é obrigatório.")
    @Digits(fraction = 2, integer = 10)
    @DecimalMin(value = "0.01", message = "O valor do ingresso não pode ser zero.")
    private BigDecimal precoIngresso;

    @NotNull(message = "A data do evento é obrigatória.")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Future(message = "A data não pode ser no passado.")
    private LocalDate dataEvento;

    @NotBlank
    private String horario;

    @NotNull(message = "Selecione uma casa de show")
    @ManyToOne
    private CasaDeShow casaDeShow;

    @NotNull(message = "Selecione gênero musical")
    @ManyToOne
    private GeneroMusical generoMusical;

    public void atualizaCapacidade(Integer qtde){
        this.capacidade = this.capacidade - qtde;
    }
}
