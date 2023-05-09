package br.com.brq.banking.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
@Data
@Entity
@Table
public class Cliente implements Serializable {

    public Cliente(String nome, LocalDateTime dataNascimento, Conta conta) {
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.conta = conta;
    }

    public Cliente() {
    }


    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "nome", length = 50, nullable = false)
    private String nome;

    private LocalDateTime dataNascimento;
    @OneToOne
    @JoinColumn(name = "conta_id")
    private Conta conta;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
