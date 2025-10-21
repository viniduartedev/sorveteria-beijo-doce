package com.sorverteria_doce_beijo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Bonificacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome; // Ex: "Promoção de Verão", "Compra padrão", etc.

    private Double valorMinimo; // Valor mínimo da compra para aplicar (ex: 8.0)

    private Integer pontosGerados; // Quantos pontos o cliente ganha

    private Boolean ativo; // Permitir ativar/desativar promoções

    private String descricao; // Texto opcional para admin se organizar
}
