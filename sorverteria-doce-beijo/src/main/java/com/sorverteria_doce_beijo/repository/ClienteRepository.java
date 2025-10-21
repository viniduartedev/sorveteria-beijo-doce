package com.sorverteria_doce_beijo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sorverteria_doce_beijo.entity.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    Optional<Cliente> findByEmail(String email);

    Optional<Cliente> findByCpf(String cpf);
}
