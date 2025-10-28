package com.sorverteria_doce_beijo.controller;

import java.util.List;
import java.util.Optional;

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

    @GetMapping
    public ResponseEntity<List<Ponto>> listarTodos() {
        return ResponseEntity.ok(pontoService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Ponto>> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(pontoService.buscarPorId(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        pontoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
