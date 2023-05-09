package br.com.brq.banking.dto;

import br.com.brq.banking.entity.Cliente;
import br.com.brq.banking.entity.Conta;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
@Data
@Builder
public class ClienteDto implements Serializable {
    private String nome;
    private LocalDateTime dataNascimento;
    private Conta conta;

    static public ClienteDto converter(Cliente cliente){
        return ClienteDto.builder()
                .nome(cliente.getNome())
                .dataNascimento(cliente.getDataNascimento())
                .conta(cliente.getConta()).build();
    }
}
