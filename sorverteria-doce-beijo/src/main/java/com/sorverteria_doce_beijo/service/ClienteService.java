package com.sorverteria_doce_beijo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sorverteria_doce_beijo.entity.Cliente;
import com.sorverteria_doce_beijo.exception.ResourceNotFoundException;
import com.sorverteria_doce_beijo.repository.ClienteRepository;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public Cliente criarCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public List<Cliente> listarTodos() {
        return clienteRepository.findAll();
    }

    public Cliente buscarPorCpf(String cpf) {
        return clienteRepository.findByCpf(cpf)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado com CPF: " + cpf));
    }

    public Cliente atualizarCliente(String cpf, Cliente clienteAtualizado) {
        Cliente cliente = buscarPorCpf(cpf);
        cliente.setNome(clienteAtualizado.getNome());
        cliente.setEmail(clienteAtualizado.getEmail());
        return clienteRepository.save(cliente);
    }

    public void deletarCliente(String cpf) {
        Cliente cliente = buscarPorCpf(cpf);
        clienteRepository.delete(cliente);
    }
}
