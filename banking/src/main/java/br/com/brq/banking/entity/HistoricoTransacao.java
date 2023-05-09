package br.com.brq.banking.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@Table
public class HistoricoTransacao {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    private String transacao;
    private LocalDateTime dataTransacao;
    private String numeroConta;
    private BigDecimal valorTransacao;

    public HistoricoTransacao() {
    }

    public HistoricoTransacao(String transacao, String numeroConta, BigDecimal valorTransacao) {
        this.dataTransacao = LocalDateTime.now();
        this.transacao = transacao;
        this.numeroConta = numeroConta;
        this.valorTransacao = valorTransacao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
