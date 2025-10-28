package com.sorverteria_doce_beijo.service;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.sorverteria_doce_beijo.entity.Bonificacao;
import com.sorverteria_doce_beijo.entity.Cliente;
import com.sorverteria_doce_beijo.entity.Ponto;
import com.sorverteria_doce_beijo.exception.ResourceNotFoundException;
import com.sorverteria_doce_beijo.repository.BonificacaoRepository;
import com.sorverteria_doce_beijo.repository.ClienteRepository;
import com.sorverteria_doce_beijo.repository.PontoRepository;

@Service
public class PontoService {

    private final PontoRepository pontoRepository;
    private final ClienteRepository clienteRepository;
    private final BonificacaoRepository bonificacaoRepository;

    public PontoService(PontoRepository pontoRepository, ClienteRepository clienteRepository,
            BonificacaoRepository bonificacaoRepository) {
        this.pontoRepository = pontoRepository;
        this.clienteRepository = clienteRepository;
        this.bonificacaoRepository = bonificacaoRepository;
    }

    public Ponto adicionarPonto(String cpf, BigDecimal valorCompra, String origem) {
        Cliente cliente = clienteRepository.findByCpf(cpf)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado com CPF: " + cpf));

        Bonificacao bonificacao = buscarBonificacaoAplicavel(valorCompra);

        int pontos = calcularPontos(valorCompra, bonificacao);

        Ponto novo = new Ponto();
        novo.setCliente(cliente);
        novo.setBonificacao(bonificacao);
        novo.setPontos(pontos);
        novo.setValorCompra(valorCompra.doubleValue());
        novo.setOrigem(origem);

        return pontoRepository.save(novo);
    }

    private Bonificacao buscarBonificacaoAplicavel(BigDecimal valorCompra) {
        return bonificacaoRepository.findAll().stream()
                .filter(b -> valorCompra.doubleValue() >= b.getValorMinimo())
                .max(Comparator.comparing(Bonificacao::getValorMinimo))
                .orElseThrow(() -> new ResourceNotFoundException("Nenhuma bonificação aplicável encontrada"));
    }

    private int calcularPontos(BigDecimal valorCompra, Bonificacao bonificacao) {
        // Exemplo: se a bonificação é "R$8 → 5 pontos", e a compra é R$32:
        // 32 / 8 = 4 × 5 = 20 pontos
        BigDecimal vezes = valorCompra.divide(BigDecimal.valueOf(bonificacao.getValorMinimo()), BigDecimal.ROUND_DOWN);
        return vezes.intValue() * bonificacao.getPontosGerados();
    }

    public void deletar(Long id) {
        Ponto ponto = pontoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Registro de ponto não encontrado"));
        pontoRepository.delete(ponto);
    }

    public List<Ponto> listarTodos() {
        return pontoRepository.findAll();
    }

    public Optional<Ponto> buscarPorId(Long id) {
        Optional<Ponto> ponto = pontoRepository.findById(id);
        return ponto;
    }
}
