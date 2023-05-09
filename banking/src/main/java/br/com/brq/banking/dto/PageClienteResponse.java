package br.com.brq.banking.dto;

import br.com.brq.banking.entity.Cliente;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.List;
import lombok.Builder;
@Builder
@JsonPropertyOrder({"currentPage", "pageSize", "totalPages", "totalElements", "clientes"})
public class PageClienteResponse {

    private Integer currentPage;
    private Integer pageSize;
    private Integer totalPages;
    private Long totalElements;
    private List<Cliente> clientes;

  /*  static public Cliente converter(Cliente cliente){
        return PageClienteResponse.builder()
                .clientes
    }*/

}
