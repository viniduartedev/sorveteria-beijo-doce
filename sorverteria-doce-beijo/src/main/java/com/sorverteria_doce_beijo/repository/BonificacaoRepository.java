package com.sorverteria_doce_beijo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sorverteria_doce_beijo.entity.Bonificacao;

@Repository
public interface BonificacaoRepository extends JpaRepository<Bonificacao, Long> {
}
