package com.sorverteria_doce_beijo.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sorverteria_doce_beijo.entity.Ponto;
import com.sorverteria_doce_beijo.service.PontoService;

@RestController
@RequestMapping("/api/pontos")
public class PontoController {

    private final PontoService pontoService;

    public PontoController(PontoService pontoService) {
        this.pontoService = pontoService;
    }

    @PostMapping("/{cpf}")
    public ResponseEntity<Ponto> registrar(@PathVariable String cpf, @RequestBody Ponto ponto) {
        return ResponseEntity.ok(pontoService.registrarPonto(cpf, ponto));
    }

    @GetMapping
    public ResponseEntity<List<Ponto>> listarTodos() {
        return ResponseEntity.ok(pontoService.listarTodos());
    }

    @GetMapping("/cliente/{cpf}")
    public ResponseEntity<List<Ponto>> listarPorCliente(@PathVariable String cpf) {
        return ResponseEntity.ok(pontoService.listarPorCliente(cpf));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ponto> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(pontoService.buscarPorId(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        pontoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
