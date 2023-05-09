package br.com.brq.banking.service;

import br.com.brq.banking.entity.Cliente;
import br.com.brq.banking.entity.Conta;
import br.com.brq.banking.entity.HistoricoTransacao;
import br.com.brq.banking.repository.ClienteRepository;
import br.com.brq.banking.repository.ContaRepository;
import br.com.brq.banking.repository.HistoricoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class BankingService {

    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private ContaRepository contaRepository;
    @Autowired
    private HistoricoRepository historicoRepository;

    public Page<Cliente> getClientes(PageRequest pageRequest) {

        return clienteRepository.findAll( pageRequest);

    }

    public List<HistoricoTransacao> getHistorico(LocalDateTime dataIni, LocalDateTime dataFim) {

        return  historicoRepository.findByDataTransacaoBetween(dataIni, dataFim);

    }

    public Conta depositar(String numeroConta, BigDecimal valor){
        Conta conta = contaRepository.findByNumero(numeroConta);
        conta.depositar(valor);
        return contaRepository.saveAndFlush(conta);
    }

    public Cliente cadastrar(Cliente cliente){
        contaRepository.saveAndFlush(cliente.getConta());
        return clienteRepository.saveAndFlush(cliente);
    }

    public HistoricoTransacao salvarHistorico(HistoricoTransacao historicoTraansacao){
        return historicoRepository.saveAndFlush(historicoTraansacao);
    }
}
