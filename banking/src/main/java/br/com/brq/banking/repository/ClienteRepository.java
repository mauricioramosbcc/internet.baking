package br.com.brq.banking.repository;

import br.com.brq.banking.entity.Cliente;
import br.com.brq.banking.entity.Conta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long>{
    Cliente findByNome(String nome);
}
