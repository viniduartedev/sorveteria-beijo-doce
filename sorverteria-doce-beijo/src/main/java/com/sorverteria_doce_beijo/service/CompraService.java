package com.sorverteria_doce_beijo.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.sorverteria_doce_beijo.dto.CompraDTO;
import com.sorverteria_doce_beijo.entity.Cliente;
import com.sorverteria_doce_beijo.entity.Compra;
import com.sorverteria_doce_beijo.exception.ResourceNotFoundException;
import com.sorverteria_doce_beijo.repository.ClienteRepository;
import com.sorverteria_doce_beijo.repository.CompraRepository;

public class CompraService {

    private final CompraRepository compraRepository;
    private final ClienteRepository clienteRepository;
    private final PontoService pontoService;
    private final ClienteService clienteService;

    public CompraService(CompraRepository compraRepository,
            ClienteRepository clienteRepository,
            PontoService pontoService,
            ClienteService clienteService) {
        this.compraRepository = compraRepository;
        this.clienteRepository = clienteRepository;
        this.pontoService = pontoService;
        this.clienteService = clienteService;
    }

    public Compra registrarCompra(String cpf, CompraDTO dto) {
        Cliente cliente = clienteRepository.findByCpf(cpf)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado com CPF: " + cpf));

        Compra novaCompra = new Compra();
        novaCompra.setCliente(cliente);
        novaCompra.setValor(dto.getValor());
        novaCompra.setDescricao(dto.getDescricao());
        novaCompra.setData(LocalDateTime.now());

        compraRepository.save(novaCompra);

        // Gera pontos automaticamente
        pontoService.adicionarPonto(cpf, dto.getValor(), "Compra");

        return novaCompra;
    }

    public List<Compra> listarCompras() {
        return compraRepository.findAll();
    }

    public List<Compra> listarComprasPorCliente(String cpf) {
        Cliente cliente = clienteService.buscarPorCpf(cpf);
        return cliente.getCompras();
    }
}
