package com.sorverteria_doce_beijo.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sorverteria_doce_beijo.entity.Compra;
import com.sorverteria_doce_beijo.service.CompraService;

@RestController
@RequestMapping("/api/compras")
public class CompraController {

    private final CompraService compraService;

    public CompraController(CompraService compraService) {
        this.compraService = compraService;
    }

    @PostMapping("/{cpf}")
    public ResponseEntity<Compra> registrarCompra(@PathVariable String cpf, @RequestBody Compra compra) {
        return ResponseEntity.ok(compraService.registrarCompra(cpf, compra));
    }

    @GetMapping
    public ResponseEntity<List<Compra>> listarTodas() {
        return ResponseEntity.ok(compraService.listarCompras());
    }

    @GetMapping("/cliente/{cpf}")
    public ResponseEntity<List<Compra>> listarPorCliente(@PathVariable String cpf) {
        return ResponseEntity.ok(compraService.listarComprasPorCliente(cpf));
    }
}
