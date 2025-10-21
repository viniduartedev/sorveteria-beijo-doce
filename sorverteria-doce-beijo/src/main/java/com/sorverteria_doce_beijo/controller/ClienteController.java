package com.sorverteria_doce_beijo.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sorverteria_doce_beijo.entity.Cliente;
import com.sorverteria_doce_beijo.exception.ResourceNotFoundException;
import com.sorverteria_doce_beijo.service.ClienteService;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @PostMapping
    public ResponseEntity<Cliente> criar(@RequestBody Cliente cliente) {
        Cliente novoCliente = clienteService.criarCliente(cliente);
        return ResponseEntity.ok(novoCliente);
    }

    @GetMapping
    public ResponseEntity<List<Cliente>> listarTodos() {
        return ResponseEntity.ok(clienteService.listarTodos());
    }

    @GetMapping("/{cpf}")
    public ResponseEntity<Cliente> buscarPorCpf(@PathVariable String cpf) {
        Cliente cliente = clienteService.buscarPorCpf(cpf);
        if (cliente == null) {
            throw new ResourceNotFoundException("Cliente não encontrado com CPF: " + cpf);
        }
        return ResponseEntity.ok(cliente);
    }

    @PutMapping("/{cpf}")
    public ResponseEntity<Cliente> atualizarCliente(@PathVariable String cpf, @RequestBody Cliente clienteAtualizado) {
        Cliente cliente = clienteService.atualizarCliente(cpf, clienteAtualizado);
        return ResponseEntity.ok(cliente);
    }

    @DeleteMapping("/{cpf}")
    public ResponseEntity<Void> deletarCliente(@PathVariable String cpf) {
        clienteService.deletarCliente(cpf);
        return ResponseEntity.noContent().build();
    }

}
