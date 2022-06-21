package com.gft.gerenciadordeeventos.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate dataPedido;

    @NotNull(message = "Informe a quantidade")
    @DecimalMin(value = "1", inclusive = true, message = "O mínimo é um ingresso.")
    private Integer quantidade;

    @Digits(integer = 10, fraction = 2)
    private BigDecimal valorTotalPedido;

    @ManyToOne(fetch = FetchType.LAZY)
    private Evento evento;

    @ManyToOne(fetch = FetchType.LAZY)
    private Usuario usuario;

    public void calcularTotalPedido(int qtdeIngressos, BigDecimal preçoIngresso){
        this.valorTotalPedido = preçoIngresso.multiply(new BigDecimal(qtdeIngressos));
    }
}
