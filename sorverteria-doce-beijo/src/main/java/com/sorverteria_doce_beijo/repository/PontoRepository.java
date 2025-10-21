package com.sorverteria_doce_beijo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sorverteria_doce_beijo.entity.Ponto;

public interface PontoRepository extends JpaRepository<Ponto, Long> {

    List<Ponto> findByClienteCpf(String cpf);

    List<Ponto> findByClienteId(Long clienteId);
}
