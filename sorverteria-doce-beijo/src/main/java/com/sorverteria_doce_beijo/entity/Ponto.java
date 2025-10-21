package com.sorverteria_doce_beijo.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Table(name = "pontos")
public class Ponto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "bonificacao_id")
    private Bonificacao bonificacao;

    private Integer pontos; // quantidade de pontos ganhos nessa operação

    private Double valorCompra;

    private String origem; // exemplo: "Compra", "Promoção", etc.

    private LocalDateTime data = LocalDateTime.now();
}
