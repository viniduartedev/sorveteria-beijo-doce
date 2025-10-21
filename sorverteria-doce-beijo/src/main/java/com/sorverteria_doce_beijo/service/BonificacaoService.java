package com.sorverteria_doce_beijo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sorverteria_doce_beijo.entity.Bonificacao;
import com.sorverteria_doce_beijo.exception.ResourceNotFoundException;
import com.sorverteria_doce_beijo.repository.BonificacaoRepository;

@Service
public class BonificacaoService {

    private final BonificacaoRepository bonificacaoRepository;

    public BonificacaoService(BonificacaoRepository bonificacaoRepository) {
        this.bonificacaoRepository = bonificacaoRepository;
    }

    public List<Bonificacao> listarTodas() {
        return bonificacaoRepository.findAll();
    }

    public Bonificacao buscarPorId(Long id) {
        return bonificacaoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Bonificação não encontrada com ID: " + id));
    }

    public Bonificacao criar(Bonificacao bonificacao) {
        bonificacao.setAtivo(true); // padrão: ativa ao criar
        return bonificacaoRepository.save(bonificacao);
    }

    public Bonificacao atualizar(Long id, Bonificacao nova) {
        Bonificacao existente = buscarPorId(id);
        existente.setNome(nova.getNome());
        existente.setValorMinimo(nova.getValorMinimo());
        existente.setPontosGerados(nova.getPontosGerados());
        existente.setDescricao(nova.getDescricao());
        existente.setAtivo(nova.getAtivo());
        return bonificacaoRepository.save(existente);
    }

    public void deletar(Long id) {
        Bonificacao existente = buscarPorId(id);
        bonificacaoRepository.delete(existente);
    }
}
