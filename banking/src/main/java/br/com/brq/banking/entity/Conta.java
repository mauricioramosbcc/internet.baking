package br.com.brq.banking.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
@Data
@Entity
@Table
public class Conta {

    public Conta() {
    }

    public Conta(String numero, BigDecimal saldo, Boolean isPlanoExclusive) {
        this.numero = numero;
        this.saldo = saldo;
        this.isPlanoExclusive = isPlanoExclusive;
    }

    public static final BigDecimal VALOR_LIMITE_ISENTO_TAXA = new BigDecimal(100);
    public static final BigDecimal TREZENTOS = new BigDecimal(300);


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    private String numero;
    private BigDecimal saldo;
    private Boolean isPlanoExclusive;


    public void sacar(BigDecimal valor){
        BigDecimal taxa = new BigDecimal(0);
        if(this.isPlanoExclusive ){
            taxa = new BigDecimal(0);
        }else if( valor.compareTo(TREZENTOS)== 1){
            taxa = new BigDecimal(0.1);
        }else if(valor.compareTo(VALOR_LIMITE_ISENTO_TAXA) == 1){
            taxa = new BigDecimal(0.4);
        }
        sacarValor(valor, taxa);

    }

    public void depositar(BigDecimal valor){
        this.saldo = this.saldo.add(valor);
    }

    private void sacarValor(BigDecimal valor, BigDecimal taxa){
        BigDecimal taxaAdm = valor.multiply(taxa);
        this.saldo = saldo.subtract(valor.add(taxaAdm));
    }
}
