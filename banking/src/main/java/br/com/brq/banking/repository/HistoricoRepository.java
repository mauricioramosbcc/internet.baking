package br.com.brq.banking.repository;

import br.com.brq.banking.entity.HistoricoTransacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface HistoricoRepository extends JpaRepository<HistoricoTransacao, Long> {
    @Query(value = "from HistoricoTransacao t where dataTransacao BETWEEN :dataIni AND :dataFim")
    public List <HistoricoTransacao> findByDataTransacaoBetween(@Param("dataIni") LocalDateTime dataIni, @Param("dataFim") LocalDateTime dataFim) ;
}
