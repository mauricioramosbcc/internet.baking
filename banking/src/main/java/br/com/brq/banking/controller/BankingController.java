package br.com.brq.banking.controller;

import br.com.brq.banking.dto.ClienteDto;
import br.com.brq.banking.entity.Cliente;
import br.com.brq.banking.entity.Conta;
import br.com.brq.banking.entity.HistoricoTransacao;
import br.com.brq.banking.service.BankingService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/v1/banking")
public class BankingController {
    @Autowired
    BankingService service;


    @GetMapping("/clientes")
    @ApiOperation(value = "Busca clientes", response = Cliente.class, responseContainer = "List")
    public ResponseEntity<Page<ClienteDto>> getClientes(@ApiParam("Page number") @RequestParam(defaultValue = "0") Integer page,
                                                        @ApiParam("Page size") @RequestParam(defaultValue = "200") Integer size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<ClienteDto> list =  service.getClientes(pageRequest).map(ClienteDto::converter);
        return ResponseEntity.ok(list);

    }

    @PutMapping("/deposito")
    @ApiOperation(value = "Deposita na conta", response = Cliente.class, responseContainer = "List")
    public ResponseEntity<Conta> depositar(@RequestParam BigDecimal valor, @RequestParam String numeroConta) {

        Conta conta = service.depositar(numeroConta, valor);
        HistoricoTransacao historicoTraansacao = new HistoricoTransacao("Deposito", numeroConta, valor);
        service.salvarHistorico(historicoTraansacao);
        return ResponseEntity.ok(conta);

    }

    @PutMapping("/sacar")
    @ApiOperation(value = "Saca da conta", response = Cliente.class, responseContainer = "List")
    public ResponseEntity.BodyBuilder sacar(@RequestParam BigDecimal valor, @RequestParam String numeroConta) {

        service.depositar(numeroConta, valor);
        Conta conta = service.depositar(numeroConta, valor);
        HistoricoTransacao historicoTraansacao = new HistoricoTransacao("saque", numeroConta, valor);
        service.salvarHistorico(historicoTraansacao);
        return ResponseEntity.ok();

    }


    @PostMapping(value = "/cadastrar", consumes = "application/json", produces = "application/json")
    @ApiOperation(value = "Cadastrar cliente ", response = Cliente.class, responseContainer = "List")
    public ResponseEntity<Cliente> cadastrar(@RequestParam String nomeCliente, @RequestParam LocalDateTime dataNascimento,
                                             @RequestParam Boolean exclisive, @RequestParam String numeroConta, @RequestParam BigDecimal saldo) {
        Conta conta = new Conta(numeroConta, saldo, exclisive);
        Cliente cliente = new Cliente(nomeCliente, dataNascimento, conta);
        cliente = service.cadastrar(cliente);

        return ResponseEntity.ok(cliente);
    }

    @GetMapping("/historico")
    @ApiOperation(value = "Historico ", response = Cliente.class, responseContainer = "List")
    public ResponseEntity<List<HistoricoTransacao>> getHistorico(@RequestParam LocalDateTime dataIni, @RequestParam LocalDateTime dataFim) {
        List<HistoricoTransacao> listHistorico = service.getHistorico( dataIni, dataFim);
        return ResponseEntity.ok(listHistorico);

    }

}
